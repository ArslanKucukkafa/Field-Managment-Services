package com.example.gateway.services.Pulsar;

import com.example.gateway.config.AppConfig;
import com.example.gateway.services.Pulsar.DtoModels.PermissionDto;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang.SerializationUtils;
import org.apache.pulsar.client.api.Consumer;
import org.apache.pulsar.client.api.Message;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.shade.com.google.gson.Gson;
import org.apache.pulsar.shade.com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;

@Service
public class PulsarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PulsarService.class);
    private PulsarClient pulsarClient;
    @Value("${pulsar.producer.url}")
    public String pulsarServiceUrl;
    public String pulsar_topic = "permission-topic";
    @Autowired
    AppConfig config;

    public HashMap<String,Object> userSessionInfo = new HashMap<>();


    @PostConstruct
    public void init () {
        try {
            pulsarClient = PulsarClient.builder()
                    .serviceUrl(pulsarServiceUrl)
                    .build();
        } catch (PulsarClientException e) {
            LOGGER.error("PulsarClient build failure!! error={}", e.getMessage());
        }
    }
    /**
     * produce
     */
    @PostConstruct
    public String produce() {

        try {
            Producer<byte[]> producer = pulsarClient.newProducer(Schema.BYTES)
                    .topic(pulsar_topic)
                    .create();

            Gson gson = new Gson();
            var scanedEndpoints = config.scanEndpoints();
            byte[] data = SerializationUtils.serialize(gson.toJson(scanedEndpoints));
            MessageId messageId = producer.send(data);
            producer.close();
            LOGGER.info(" pulsar_topic={} pulsar_messageId={}", pulsar_topic, messageId);
            return "topic=" + pulsar_topic + "messageId=" + messageId;
        } catch (Exception e) {
            LOGGER.error("Pulsar produce failure!! error={}", e.getMessage());
            return "Pulsar produce failure!! error=" + e.getMessage();
        }
    }

    @PostConstruct
    public void listenLoginUser(){
        Gson gson = new Gson();
        new Thread(()-> {
            try {
                LOGGER.info("ARSLAN---------ARSLAN");
                Consumer<byte[]> consumer = pulsarClient.newConsumer(Schema.BYTES)
                        .topic("login-user")
                        .subscriptionName("login-user")
                        .subscribe();
                while (!Thread.currentThread().isInterrupted()) {
                    Message<byte[]> message = consumer.receive();
                    byte[] data = message.getData();
                    var deserializeData = SerializationUtils.deserialize(data);
                    Type tokenType = new TypeToken<HashMap<String,Object>>(){}.getType();
                    userSessionInfo = gson.fromJson(deserializeData.toString(), tokenType);
                    LOGGER.info("topic={},message={},messageId={}", "login-user", deserializeData, message.getMessageId().toString());
                    consumer.acknowledge(message);
                    Thread.sleep(20);
                }

            } catch (Exception e){
                LOGGER.error("Pulsar consume failure!! error={}", e.getMessage());
            }
        }).start();
    }
}
