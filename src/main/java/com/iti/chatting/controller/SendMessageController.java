package com.iti.chatting.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.iti.chatting.ChattingApplication.MAIN_EXCHANGE;

@RestController
public class SendMessageController {
    private final RabbitTemplate rabbitTemplate;

    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public String sendMessage(@RequestParam String message,
                              @RequestParam String chatId,
                              @RequestParam String toUserId) {
        String routingKey = chatId + "/" + toUserId;
        rabbitTemplate.convertAndSend(MAIN_EXCHANGE,
                routingKey, message);
        return "Message Sent Successfully";
    }
}
