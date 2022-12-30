package com.iti.chatting.Mapper;

import com.iti.chatting.dto.MessageRequestDto;
import com.iti.chatting.dto.MessageResponseDto;
import com.iti.chatting.model.MessageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MessageMapper {
    MessageMapper INSTANCE = Mappers.getMapper(MessageMapper.class);

    MessageEntity MessageRequestDtoToMessage(MessageRequestDto messageRequestDtoDto);
    MessageResponseDto MessageToMessageResponseDto(MessageEntity messageEntity);
}
