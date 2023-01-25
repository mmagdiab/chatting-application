package com.iti.chatting.controller;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.service.Impl.ChatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class UIChatsController {

    @Autowired
    ChatServiceImpl chatService;
    @GetMapping("/chats")
    public ModelAndView chats(Model model) {
        List<ChatEntity> chats = chatService.findAll();
        model.addAttribute("chats", chats);
        ModelAndView mav = new ModelAndView("/chats");
        return mav;
    }

    @PostMapping("/chats")
    public String chats(@RequestParam String topic) {
        ChatEntity chatEntity = new ChatEntity();
        chatEntity.setTopic(topic);
        chatService.addChat(chatEntity);
        return "redirect:/ui/chats/";
    }
}
