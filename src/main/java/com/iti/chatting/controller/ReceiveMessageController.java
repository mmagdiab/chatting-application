package com.iti.chatting.controller;

import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.MessageService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ui/room")
public class ReceiveMessageController {

    private final MessageService messageService;

    public ReceiveMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/newMessages")
    @ResponseBody
    public String getMessages(@RequestParam String roomId, Authentication authentication) {
        UserEntity receiver = (UserEntity) authentication.getPrincipal();
        String receiverId = receiver.getId();
        return messageService.getMessage(roomId, receiverId);
    }
}
