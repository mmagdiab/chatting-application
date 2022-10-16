package com.iti.chatting.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MessageResponseDto {

    private String text;
    private String userName;
    private String chatTopic;

}
