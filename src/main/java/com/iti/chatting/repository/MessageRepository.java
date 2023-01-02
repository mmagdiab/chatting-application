package com.iti.chatting.repository;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageEntity, String> {
    List<MessageEntity> findByChat(ChatEntity chatEntity);
}
