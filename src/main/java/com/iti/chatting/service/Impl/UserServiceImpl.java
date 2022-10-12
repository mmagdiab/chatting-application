package com.iti.chatting.service.Impl;

import com.iti.chatting.model.UserEntity;
import com.iti.chatting.repository.UserRepository;
import com.iti.chatting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserEntity addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        if (!userRepository.existsById(userEntity.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not found!");
        }
        userRepository.save(userEntity);
        return userEntity;
    }
}
