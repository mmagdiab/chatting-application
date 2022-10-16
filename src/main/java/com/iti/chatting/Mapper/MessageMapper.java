package com.iti.chatting.Mapper;

import com.iti.chatting.dto.MessageRequestDto;
import com.iti.chatting.dto.MessageResponseDto;
import com.iti.chatting.model.MessageEntity;

public class MessageMapper {

    public static MessageEntity fromMessageRequestToMessageEntity(MessageRequestDto requestDto){
        MessageEntity entity = new MessageEntity();
        entity.setText(requestDto.getText());
        return entity;
    }

    public static MessageResponseDto fromMessageEntityToMessageResponse(MessageEntity entity){
        return MessageResponseDto.builder().chatTopic(entity.getChat().getTopic()).userName(entity.getUser().getName()).text(entity.getText()).build();
    }
}
