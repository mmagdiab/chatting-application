package com.iti.chatting.controller;

import com.iti.chatting.Mapper.ChatMapper;
import com.iti.chatting.dto.ChatDto;
import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.Impl.ChatServiceImpl;
import com.iti.chatting.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/chats")
    public ResponseEntity addChat(@RequestBody ChatDto requestDto){
        return new ResponseEntity(ChatMapper.INSTANCE.ChatToChatDto(chatService.addChat(ChatMapper.INSTANCE.ChatDtoToChat(requestDto))), HttpStatus.OK);

    }

    @PostMapping("/chats/{chatid}/user/{userid}")
    public ResponseEntity addUserToChat(@PathVariable(name = "userid") String userId, @PathVariable(name = "chatid") String chatID){
        UserEntity user = userService.findByID(userId);
        Optional<ChatEntity> chat = chatService.findByID(chatID);
        if(!chat.isPresent()) return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);

        ChatEntity response = chatService.addUserToChat(user, chat.get());
        /*else {
            chat.get().getUser().add(user);
            ChatEntity response = chatService.addChat(chat.get());
            return new ResponseEntity(ChatMapper.fromChatEntityToChatResponse(response),HttpStatus.OK);
        }*/
        return new ResponseEntity(ChatMapper.INSTANCE.ChatToChatDto(response), HttpStatus.OK);
    }
}
