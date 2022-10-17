package com.iti.chatting.service;

import com.iti.chatting.model.UserEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    public UserEntity addUser(UserEntity userEntity);
    public UserEntity updateUser(UserEntity userEntity);

    UserEntity findByID(Long userId);
}
