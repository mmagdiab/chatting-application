package com.iti.chatting.controller;

import com.iti.chatting.model.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class HomeController {
    @GetMapping("/home")
    public String showHomePage(Authentication authentication, Model model) {
        String username = ((UserEntity)authentication.getPrincipal()).getUsername();
        model.addAttribute("username", username);
        return "home";
    }
}
