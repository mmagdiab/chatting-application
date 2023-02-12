package com.iti.chatting.service.Impl;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.MessageEntity;
import com.iti.chatting.repository.MessageRepository;
import com.iti.chatting.service.MessageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private MessageRepository messageRepository;


    public MessageServiceImpl(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }

    public MessageEntity addMessage(MessageEntity message){
        return messageRepository.save(message);
    }

    public List<MessageEntity> findByChat(ChatEntity chatEntity) {
        return messageRepository.findByChat(chatEntity);
    }

    @Override
    public MessageEntity sendMessage(MessageEntity message) {
        return null;
    }
}
