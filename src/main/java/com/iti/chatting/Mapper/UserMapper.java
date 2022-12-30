package com.iti.chatting.Mapper;

import com.iti.chatting.dto.UserRequestDto;
import com.iti.chatting.dto.UserResponseDto;
import com.iti.chatting.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.control.MappingControl;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserEntity UserRequestDtoToUser(UserRequestDto userRequestDto);
    UserResponseDto UserToUserResponseDto(UserEntity UserEntity);
}
