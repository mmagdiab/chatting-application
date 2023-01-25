package com.iti.chatting.controller;

import com.iti.chatting.dto.UserRequestDto;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserEntity user, Model model) {
        boolean isSuccess = userService.saveUser(user) != null;
        if (isSuccess) {
            model.addAttribute("success", "Registration Successful!");
            model.addAttribute("user", new UserEntity());
            return "register";
        } else {
            model.addAttribute("error", "Registration Unsuccessful!");
            model.addAttribute("user", new UserEntity());
            return "register";
        }
    }
}


