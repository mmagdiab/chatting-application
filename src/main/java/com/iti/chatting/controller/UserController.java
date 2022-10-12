package com.iti.chatting.controller;

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
    public ResponseEntity addUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok().body(userService.addUser(userEntity));
    }

    @PutMapping("/user/update")
    public ResponseEntity updatrUser(@RequestBody UserEntity userEntity) {
        return ResponseEntity.ok().body(userService.updateUser(userEntity));
    }
}
