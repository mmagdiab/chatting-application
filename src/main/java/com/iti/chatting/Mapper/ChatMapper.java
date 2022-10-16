package com.iti.chatting.Mapper;

import com.iti.chatting.dto.ChatRequestDto;
import com.iti.chatting.dto.ChatResponseDto;
import com.iti.chatting.model.ChatEntity;

public class ChatMapper {

    public static ChatEntity fromChatRequestToChatEntity(ChatRequestDto requestDto){
        ChatEntity response = new ChatEntity();
        response.setTopic(requestDto.getTopic());
        response.setPassword(requestDto.getPassword());
        return response;
    }

    public static ChatResponseDto fromChatEntityToChatResponse(ChatEntity entity){
        return ChatResponseDto.builder().topic(entity.getTopic()).password(entity.getPassword()).build();
    }
}
