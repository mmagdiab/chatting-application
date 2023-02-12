package com.iti.chatting.service;

import com.iti.chatting.model.MessageEntity;

public interface MessageService {
    MessageEntity sendMessage(MessageEntity message);
}
