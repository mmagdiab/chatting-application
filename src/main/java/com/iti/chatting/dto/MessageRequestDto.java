package com.iti.chatting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageRequestDto {
    private String text;
}