package com.iti.chatting.controller;

import com.iti.chatting.Mapper.UserMapper;
import com.iti.chatting.dto.UserRequestDto;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/user/add")
    public ResponseEntity addUser(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok().body(UserMapper.fromUserEntityToUserResponse(userService.addUser(UserMapper.fromUserRequestToUserEntity(requestDto))));
    }

    @PutMapping("/user/update")
    public ResponseEntity updatrUser(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.ok().body(UserMapper.fromUserEntityToUserResponse(userService.updateUser(UserMapper.fromUserRequestToUserEntity(requestDto))));
    }
}
