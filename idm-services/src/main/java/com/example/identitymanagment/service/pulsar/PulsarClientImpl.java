package com.example.identitymanagment.service.pulsar;

import jakarta.annotation.PostConstruct;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PulsarClientImpl {

    private final Logger LOGGER = LoggerFactory.getLogger(PulsarClientImpl.class);

    PulsarClient pulsarClient;

    @Value("${pulsar.connection.url}")
    private String pulsarServiceUrl;

    public PulsarClientImpl(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
    }

    /**
     * PulsarClient initialaize and conf method
     */

    @PostConstruct
    public void init() {
        try {
            pulsarClient = org.apache.pulsar.client.api.PulsarClient.builder()
                    .serviceUrl(pulsarServiceUrl)
                    .build();
        } catch (PulsarClientException e) {
            LOGGER.error("PulsarClient build failure!! error={}", e.getMessage());
        }
    }
}
