package com.iti.chatting.dto;

import com.iti.chatting.model.UserEntity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequestDto {
    private String text;
    private UserEntity user;
}
