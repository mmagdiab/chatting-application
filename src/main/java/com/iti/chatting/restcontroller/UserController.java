package com.iti.chatting.restcontroller;

import com.iti.chatting.Mapper.UserMapper;
import com.iti.chatting.dto.UserRequestDto;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody @Valid UserRequestDto userRequestDto) {
        return ResponseEntity.ok().body(
                UserMapper.INSTANCE.UserToUserResponseDto(
                        userService.addUser(UserMapper.INSTANCE.UserRequestDtoToUser(userRequestDto)
                        )
                )
        );
    }

    @PutMapping("/users")
    public ResponseEntity updateUser(@RequestBody UserEntity userEntity) {
        // TODO: UPDATE WITH A PROPER MAPPER
        return ResponseEntity.ok().body(userService.updateUser(userEntity));
    }
}
