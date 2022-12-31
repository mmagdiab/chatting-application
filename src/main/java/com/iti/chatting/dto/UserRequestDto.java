package com.iti.chatting.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserRequestDto {
    @Size(min = 5,message = "User name must be at least 5 characters")
    @NotNull(message = "username cannot be null")
    private String username;

    private String password;


}
