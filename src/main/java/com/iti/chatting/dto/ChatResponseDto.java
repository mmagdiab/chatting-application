package com.iti.chatting.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatResponseDto {
    private String topic;
    private String password;
}
