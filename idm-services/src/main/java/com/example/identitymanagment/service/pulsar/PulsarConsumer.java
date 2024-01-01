package com.example.identitymanagment.service.pulsar;


import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.repository.PermissionRepository;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang.SerializationUtils;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.shade.com.google.gson.Gson;
import org.apache.pulsar.shade.com.google.gson.internal.LinkedTreeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PulsarConsumer {
    @Autowired
    PermissionRepository permissionRepository;
    private String pulsar_topic = "permission-topic";

    private final PulsarClient pulsarClient;

    private final PulsarClientImpl pulsarClientImpl;
    public final Logger LOGGER = LoggerFactory.getLogger(PulsarConsumer.class);

    public PulsarConsumer(PulsarClient pulsarClient, PulsarClientImpl pulsarClientImpl) {
        this.pulsarClient = pulsarClient;
        this.pulsarClientImpl = pulsarClientImpl;
    }


    /**
     * consume
     */
    @PostConstruct
    public void saveAllPermission() {
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
                    List<LinkedTreeMap> list = gson.fromJson(deserializeData.toString(), List.class);
                    for (var map : list) {
                        var permission = new Permission(map.get("url").toString() ,map.get("http_type").toString());
                        LOGGER.info("save to permission repository {}", map.get("url").toString());
                        permissionRepository.save(permission);
                    }

                    consumer.acknowledge(message);
                    MessageId messageId = message.getMessageId();
                    LOGGER.info("topic={},message={},messageId={}", pulsar_topic, data, messageId.toString());
                    Thread.sleep(20);
                }
            } catch (Exception e) {
                LOGGER.error("Pulsar consume failure in saveAllPermission method!! error={}", e.getMessage());
            }
        }).start();
    }

}
