package com.iti.chatting.controller;

import com.iti.chatting.model.ChatEntity;
import com.iti.chatting.model.MessageEntity;
import com.iti.chatting.repository.ChatRepository;
import com.iti.chatting.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/ui")
public class UIChatController {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    ChatRepository chatRepository;
    @GetMapping("/chat/{chat_id}")
    public ModelAndView chat(Model model, @PathVariable String chat_id) {
        ChatEntity chatEntity = chatRepository.findById(chat_id).get();
        List<MessageEntity> messages = messageRepository.findByChat(chatEntity);
        model.addAttribute("messages", messages);
        ModelAndView mav = new ModelAndView("/chat");
        return mav;
    }
}
