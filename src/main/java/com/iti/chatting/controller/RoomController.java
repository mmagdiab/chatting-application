package com.iti.chatting.controller;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.service.Impl.ChatServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ui/room")
public class RoomController {
    private final ChatServiceImpl chatService;

    public RoomController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/{id}")
    public String showRoom(@PathVariable("id") String roomId, Model model) {
        ChatEntity room = chatService.findByID(roomId).get();
        model.addAttribute("room", room);
        return "room";
    }

    //TODO: ADD send message
    @PostMapping
    public String sendMessage() {
        return "redirect:/room";
    }
}
