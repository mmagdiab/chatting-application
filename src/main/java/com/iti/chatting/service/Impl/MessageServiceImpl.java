package com.iti.chatting.service.Impl;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.MessageEntity;
import com.iti.chatting.repository.MessageRepository;
import com.iti.chatting.service.MessageService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    final private MessageRepository messageRepository;
    final private RabbitTemplate rabbitTemplate;

    public MessageServiceImpl(MessageRepository messageRepository, RabbitTemplate rabbitTemplate){
        this.messageRepository = messageRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public MessageEntity addMessage(MessageEntity message){
        return messageRepository.save(message);
    }

    public List<MessageEntity> findByChat(ChatEntity chatEntity) {
        return messageRepository.findByChat(chatEntity);
    }

    @Override
    public MessageEntity sendMessage(MessageEntity message) {
        String mainExchange = "mainExchange";
        String routingKey = message.getChat().getId();
        String senderUsername = message.getUser().getUsername();

        rabbitTemplate.convertAndSend(mainExchange, routingKey, message.getText(), m -> {
            m.getMessageProperties().getHeaders().put("sender", senderUsername);
            return m;
        });
        return messageRepository.save(message);
    }
}
