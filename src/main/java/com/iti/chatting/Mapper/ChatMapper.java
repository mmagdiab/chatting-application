package com.iti.chatting.Mapper;

import com.iti.chatting.dto.ChatDto;
import com.iti.chatting.model.ChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);


    ChatDto ChatToChatDto(ChatEntity chat);

    ChatEntity ChatDtoToChat(ChatDto chatDto);
}
