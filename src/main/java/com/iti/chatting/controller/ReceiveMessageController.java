package com.iti.chatting.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReceiveMessageController {
    private RabbitTemplate rabbitTemplate;

    public ReceiveMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/receive")
    public String receiveMessage(@RequestParam String userName) {
        return (String) rabbitTemplate.receiveAndConvert(userName + "Queue", String.class.getModifiers());
    }
}
