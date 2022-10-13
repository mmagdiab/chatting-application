package com.iti.chatting.controller;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.Impl.ChatServiceImpl;
import com.iti.chatting.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class ChatController {

    private ChatServiceImpl chatService;
    private UserService userService;

    public ChatController(ChatServiceImpl chatService,UserService userService)
    {
        this.chatService = chatService;
        this.userService = userService;
    }

    @PostMapping("/chat/add")
    public ResponseEntity addChat(@RequestBody ChatEntity entity){
        return new ResponseEntity(chatService.addChat(entity), HttpStatus.OK);

    }

    @GetMapping("/chat/adduser")
    public ResponseEntity addUserToChat(@RequestBody Long userId,@RequestBody Long chatID){
        UserEntity user = userService.findByID(userId);
        Optional<ChatEntity> chat = chatService.findByID(chatID);
        if(!chat.isPresent()) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        else {
            chat.get().getUser().add(user);
            ChatEntity response = chatService.addChat(chat.get());
            return new ResponseEntity(response,HttpStatus.OK);
        }
    }
}
