package com.iti.chatting.controller;

import com.iti.chatting.model.UserEntity;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

import javax.validation.Valid;

import java.security.Principal;

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
                              Authentication authentication) {
        Object principal = authentication.getPrincipal();
        String username = "";
        if (principal instanceof UserEntity) {
            username = ((UserEntity)principal).getId();
            System.out.println("HELLO HELLO");
        }
        String name = username;
        // String name = authentication.getName();
        String routingKey = chatId;
        rabbitTemplate.convertAndSend(MAIN_EXCHANGE, routingKey, message, m -> {
            m.getMessageProperties().getHeaders().put("sender", name);
            return m;
        });
        return "Message Sent Successfully.";
    }
}
