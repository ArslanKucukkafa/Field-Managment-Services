package com.example.identitymanagment.service.pulsar;


import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.repository.PermissionRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang.SerializationUtils;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.shade.com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PulsarConsumer {
    @Autowired
    PermissionRepository permissionRepository;

    @Value("${pulsar.consumer.url}")
    private String pulsarServiceUrl;
    private String pulsar_topic = "permission-topic";
    private PulsarClient pulsarClient;
    public final Logger LOGGER = LoggerFactory.getLogger(PulsarConsumer.class);


    @PostConstruct
    public void init() {
        try {
            pulsarClient = PulsarClient.builder()
                    .serviceUrl(pulsarServiceUrl)
                    .build();
        } catch (PulsarClientException e) {
            LOGGER.error("PulsarClient build failure!! error={}", e.getMessage());
        }
    }

    /**
     * consume
     */
    @PostConstruct
    public void consume() {
        Gson gson = new Gson();
        new Thread(() -> {
            try {
                Consumer<byte[]> consumer = pulsarClient.newConsumer(Schema.BYTES)
                        .topic(pulsar_topic)
                        .subscriptionName(pulsar_topic)
                        .subscribe();

                while (!Thread.currentThread().isInterrupted()) {
                    Message<byte[]> message = consumer.receive();
                    byte[] data = message.getData();
                    var deserializeData = SerializationUtils.deserialize(data);
                    System.out.println("👉👉👉👉👉👉👉 SHIT");
                    List<ArrayList> list = gson.fromJson(deserializeData.toString(), List.class);
                     List<Permission> permissionList = new ArrayList<>();
                    for (var set : list) {
                        var permission = new Permission(set.get(0).toString(),set.get(1).toString());
                        LOGGER.info("save to permission repository {}",set.get(0).toString());
                        permissionRepository.save(permission);
                    }

                    consumer.acknowledge(message);
                    MessageId messageId = message.getMessageId();
                    LOGGER.info("topic={},message={},messageId={}", pulsar_topic, data, messageId.toString());
                    Thread.sleep(20);
                }
            } catch (Exception e) {
                LOGGER.error("Pulsar consume failure!! error={}", e.getMessage());
            }
        }).start();
    }

}
