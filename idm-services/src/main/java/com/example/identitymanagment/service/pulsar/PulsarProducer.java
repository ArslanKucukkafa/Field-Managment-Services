package com.example.identitymanagment.service.pulsar;

import com.example.identitymanagment.entity.Permission;
import com.example.identitymanagment.entity.Role;
import com.example.identitymanagment.repository.PermissionRepository;
import org.apache.commons.lang.SerializationUtils;
import org.apache.pulsar.client.api.MessageId;
import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.apache.pulsar.shade.com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component
public class PulsarProducer {
    @Autowired
    PermissionRepository permissionRepository;
    public String login_user = "login-user";
    private final PulsarClient pulsarClient;
    public final Logger LOGGER = LoggerFactory.getLogger(PulsarProducer.class);
    public PulsarProducer(PulsarClient pulsarClient) {
        this.pulsarClient = pulsarClient;
    }

    public void currentRoleSendPulsar (HashMap<String,Object> userSessionInfo){
            try {
                Producer<byte[]> producer = pulsarClient.newProducer(Schema.BYTES)
                        .topic(login_user)
                        .create();
                Gson gson = new Gson();
                byte[] data = SerializationUtils.serialize(gson.toJson(userSessionInfo));
                MessageId messageId = producer.send(data);
                producer.close();
                LOGGER.info("pulsar_topic={} pulsar_messageId={}", login_user, messageId);
            } catch (PulsarClientException ex){
                LOGGER.error("Pulsar produce failure!! error={}", ex.getMessage());
            }
        }
}

