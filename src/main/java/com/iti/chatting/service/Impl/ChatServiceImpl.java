package com.iti.chatting.service.Impl;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.repository.ChatRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@NoArgsConstructor
public class ChatServiceImpl {

    private ChatRepository chatRepository;

    public ChatServiceImpl(ChatRepository repository){
        this.chatRepository = repository;
    }

    public ChatEntity addChat(ChatEntity entity){
        return chatRepository.save(entity);
    }

    public List<ChatEntity> findAll() {
        return chatRepository.findAll();
    }
    public void removeAll(){
        chatRepository.deleteAll();
    }
    public Optional<ChatEntity> findByID(Long id){
        return chatRepository.findById(id);
    }
}
