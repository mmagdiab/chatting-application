package com.iti.chatting.controller;

import com.iti.chatting.model.UserEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.iti.chatting.ChattingApplication.MAIN_EXCHANGE;

@RestController
public class SendMessageController {
    private final RabbitTemplate rabbitTemplate;

    public SendMessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping("/send")
    public String sendMessageToChat(@RequestParam String message,
                                    @RequestParam String chatId,
                                    Authentication authentication) {
        String sender_id = ((UserEntity)authentication.getPrincipal()).getId();
        String routingKey = chatId;
        rabbitTemplate.convertAndSend(MAIN_EXCHANGE, routingKey, message, m -> {
            m.getMessageProperties().getHeaders().put("sender", sender_id);
            return m;
        });
        return "Message Sent Successfully.";
    }
}
