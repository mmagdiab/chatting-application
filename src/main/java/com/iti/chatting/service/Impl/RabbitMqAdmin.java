package com.iti.chatting.service.Impl;

import com.iti.chatting.model.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.iti.chatting.ChattingApplication.MAIN_EXCHANGE;

@Service
@Slf4j
public class RabbitMqAdmin {
    @Autowired
    private AmqpAdmin admin;
    private final String ROUTING_KEY_GROUP = "group";
    public void createUserQueue(String userName) {
        String queueName = userName + "Queue";
        Queue queue = new Queue(queueName, true, false, false);
        Binding binding_group = new Binding(queueName, Binding.DestinationType.QUEUE, MAIN_EXCHANGE, ROUTING_KEY_GROUP, null);
        Binding binding_private = new Binding(queueName, Binding.DestinationType.QUEUE, MAIN_EXCHANGE, userName, null);
        admin.declareQueue(queue);
        admin.declareBinding(binding_group);
        admin.declareBinding(binding_private);
        log.info("Queue created for user: " + userName);
    }
}
