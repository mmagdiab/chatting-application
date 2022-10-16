package com.iti.chatting.controller;

import com.iti.chatting.Mapper.MessageMapper;
import com.iti.chatting.dto.MessageRequestDto;
import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.MessageEntity;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.Impl.ChatServiceImpl;
import com.iti.chatting.service.Impl.MessageService;
import com.iti.chatting.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@AllArgsConstructor
public class MessageController {

    private MessageService messageService;
    private UserService userService;
    private ChatServiceImpl chatService;


    @PostMapping("/message/add")
    public ResponseEntity addMessageByUserToChat(@RequestBody MessageRequestDto requestDto, Long userId, Long chatId){
        UserEntity user = userService.findByID(userId);
        Optional<ChatEntity> chat = chatService.findByID(chatId);
        if(!chat.isPresent()) return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        else{
            MessageEntity message = MessageMapper.fromMessageRequestToMessageEntity(requestDto);
            ChatEntity chatEntity = chat.get();
            message.setChat(chatEntity);
            message.setUser(user);
            messageService.addMessage(message);
            return new ResponseEntity(message,HttpStatus.OK);
        }

    }

}
