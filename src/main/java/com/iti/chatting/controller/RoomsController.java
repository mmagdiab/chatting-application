package com.iti.chatting.controller;

import com.iti.chatting.Mapper.ChatMapper;
import com.iti.chatting.dto.ChatDto;
import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.Impl.ChatServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/ui")
public class RoomsController {
    private final ChatServiceImpl chatService;
    public RoomsController(ChatServiceImpl chatService) {
        this.chatService = chatService;
    }
    @GetMapping("/rooms")
    public String showRooms(@ModelAttribute ChatEntity newRoom, Model model) {
        List<ChatEntity> rooms = chatService.findAll();
        model.addAttribute("rooms", rooms);
        model.addAttribute("newRoom", newRoom);
        return "rooms";
    }
    @PostMapping("/rooms/create")
    public String createRoom(@ModelAttribute ChatEntity newRoom) {
        chatService.addChat(newRoom);
        return "redirect:/ui/rooms";
    }
    @PostMapping("/rooms/join")
    public String joinRoom(@RequestParam String roomId, Authentication authentication) {
        UserEntity user = ((UserEntity) authentication.getPrincipal());
        ChatEntity chat = chatService.findByID(roomId).get();
        chatService.addUserToChat(user, chat);
        return "redirect:/ui/room/" + roomId;
    }
}
