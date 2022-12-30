package com.iti.chatting.controller;

import com.iti.chatting.model.UserEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveMessageController {
    private RabbitTemplate rabbitTemplate;

    public ReceiveMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/receive")
    public String receiveMessageFromChat(@RequestParam String chatId,
                                         Authentication authentication) {
        String user_id = ((UserEntity)authentication.getPrincipal()).getId();
        String queueName = chatId + "/" + user_id;
        return rabbitTemplate.receive(queueName).toString();
        // (String) rabbitTemplate.receiveAndConvert(queueName, String.class.getModifiers());
    }
}
