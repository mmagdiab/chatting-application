package com.iti.chatting.service;

import com.iti.chatting.model.MessageEntity;

import java.util.List;

public interface MessageService {
    MessageEntity sendMessage(MessageEntity message);

    String getMessage(String roomId, String receiverId);
}
