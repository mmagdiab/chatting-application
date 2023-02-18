package com.iti.chatting.controller;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.MessageEntity;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.service.Impl.ChatServiceImpl;
import com.iti.chatting.service.MessageService;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.context.support.SecurityWebApplicationContextUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ui/room")
public class RoomController {

    private final ChatServiceImpl chatService;
    private final MessageService messageService;
    public RoomController(ChatServiceImpl chatService, MessageService messageService) {
        this.chatService = chatService;
        this.messageService = messageService;
    }

    @GetMapping("/{id}")
    public String showRoom(@PathVariable("id") String roomId, Model model) {
        ChatEntity room = chatService.findByID(roomId).orElseThrow(
                RuntimeException::new
        );
        model.addAttribute("room", room);
        return "room";
    }

    @PostMapping("/sendMessage")
    public String sendMessage(@RequestParam String roomId,
                              @RequestParam String messageText,
                              Authentication authentication) {
        UserEntity sender = (UserEntity) authentication.getPrincipal();
        ChatEntity room = chatService.findByID(roomId).orElseThrow(
                RuntimeException::new
        );
        MessageEntity newMessage = MessageEntity.builder()
                .text(messageText)
                .chat(room)
                .user(sender)
                .build();
        messageService.sendMessage(newMessage);
        return "redirect:/ui/room/" + roomId;
    }

    // TODO: not working, fix
    @GetMapping("/newMessages")
    @ResponseBody
    public String getMessages(@RequestParam String roomId, Authentication authentication) {
        UserEntity receiver = (UserEntity) authentication.getPrincipal();
        String receiverId = receiver.getId();
        return messageService.getMessage(roomId, receiverId);
    }
}
