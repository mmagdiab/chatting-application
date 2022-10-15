package com.iti.chatting.service.Impl;

import com.iti.chatting.model.MessageEntity;
import com.iti.chatting.repository.MessageRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private MessageRepository messageRepository;


    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public MessageEntity addMessage(MessageEntity message){
        return messageRepository.save(message);
    }
}
