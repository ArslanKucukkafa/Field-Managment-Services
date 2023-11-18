package com.example.gateway.services;

import com.example.gateway.config.AppConfig;
import jakarta.annotation.PostConstruct;
import org.apache.commons.lang.SerializationUtils;
import org.apache.pulsar.client.api.*;
import org.apache.pulsar.shade.com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class PulsarProducer {

    private static final Logger LOG = LoggerFactory.getLogger(PulsarProducer.class);
    private PulsarClient pulsarClient;
    @Value("${pulsar.producer.url}")
    public String pulsarServiceUrl;
    public String pulsar_topic = "permission-topic";


    @Autowired
    AppConfig config;

    @PostConstruct
    public void init () {
        try {
            pulsarClient = PulsarClient.builder()
                    .serviceUrl(pulsarServiceUrl)
                    .build();
        } catch (PulsarClientException e) {
            LOG.error("PulsarClient build failure!! error={}", e.getMessage());
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
            LOG.info(" pulsar_topic={} pulsar_messageId={}", pulsar_topic, messageId);
            return "topic=" + pulsar_topic + "messageId=" + messageId;
        } catch (Exception e) {
            LOG.error("Pulsar produce failure!! error={}", e.getMessage());
            return "Pulsar produce failure!! error=" + e.getMessage();
        }
    }

}
