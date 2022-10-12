package com.iti.chatting.service;

import com.iti.chatting.model.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    public UserEntity addUser(UserEntity userEntity);
    public UserEntity updateUser(UserEntity userEntity);
}
