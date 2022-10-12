package com.iti.chatting.controller;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.service.Impl.ChatServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatController {

    private ChatServiceImpl chatService;

    public ChatController(ChatServiceImpl chatService){
        this.chatService = chatService;
    }

    @PostMapping("/chat/add")
    public ChatEntity addChat(@RequestBody ChatEntity entity){
        System.out.println(entity.getId());
        System.out.println(entity.getTopic());
        System.out.println(entity.getPassword());
        return this.chatService.addChat(entity);

    }

    @GetMapping("/chat")
    public List<ChatEntity> getAllChats(){
        return chatService.findAll();
    }
}
