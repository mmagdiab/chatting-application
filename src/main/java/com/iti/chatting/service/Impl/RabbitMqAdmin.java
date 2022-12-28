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

    public void createQueue(String chatId, String userId) {
        String queueName = chatId + "/" + userId;
        Queue queue = new Queue(queueName, true, false, false);
        Binding binding = new Binding(queueName, Binding.DestinationType.QUEUE, MAIN_EXCHANGE, queueName, null);
        admin.declareQueue(queue);
        admin.declareBinding(binding);
        log.info("Queue created for: " + chatId + "/" + userId);
    }
}
