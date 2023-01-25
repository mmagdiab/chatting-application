package com.iti.chatting.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui")
public class aboutController {
    @GetMapping("/about")
    String showAboutPage() {
        return "about";
    }
}
