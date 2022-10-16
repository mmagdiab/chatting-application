package com.iti.chatting.Mapper;

import com.iti.chatting.dto.UserRequestDto;
import com.iti.chatting.dto.UserResponseDto;
import com.iti.chatting.model.UserEntity;

public class UserMapper {

    public static UserEntity fromUserRequestToUserEntity(UserRequestDto requestDto){
        UserEntity entity = new UserEntity();
        entity.setName(requestDto.getName());
        return entity;
    }

    public static UserResponseDto fromUserEntityToUserResponse(UserEntity entity){
        return UserResponseDto.builder().name(entity.getName()).build();
    }

}
