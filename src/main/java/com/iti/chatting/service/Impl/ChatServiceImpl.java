package com.iti.chatting.service.Impl;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.repository.ChatRepository;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ChatServiceImpl {

    @Autowired
    private ChatRepository chatRepository;

    @Autowired
    private RabbitMqAdmin rabbitMqAdmin;

    public ChatEntity addChat(ChatEntity entity){
        return chatRepository.saveAndFlush(entity);
    }

    public List<ChatEntity> findAll() {
        return chatRepository.findAll();
    }
    public void removeAll(){
        chatRepository.deleteAll();
    }
    public Optional<ChatEntity> findByID(String id){
        return chatRepository.findById(id);
    }

    public ChatEntity addUserToChat(UserEntity user, ChatEntity chatEntity) {
        chatEntity.getUser().add(user);
        // Create queue for this group/chat
        rabbitMqAdmin.createQueue(chatEntity.getId().toString(), user.getId().toString());
        return addChat(chatEntity);
    }
}
