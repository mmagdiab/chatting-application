package com.iti.chatting.restcontroller;

import com.iti.chatting.dto.MessageRequestDto;
import com.iti.chatting.model.MessageEntity;
import com.iti.chatting.model.UserEntity;
import com.iti.chatting.repository.ChatRepository;
import com.iti.chatting.repository.MessageRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.iti.chatting.ChattingApplication.MAIN_EXCHANGE;

@RestController
public class SendMessageController {
    private final RabbitTemplate rabbitTemplate;
    private ChatRepository chatRepository;
    private MessageRepository messageRepository;

    public SendMessageController(RabbitTemplate rabbitTemplate,
                                 ChatRepository chatRepository,
                                 MessageRepository messageRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/send")
    public String sendMessageToChat(@RequestParam String message,
                                    @RequestParam String chatId,
                                    Authentication authentication) {
        UserEntity userEntity = (UserEntity)authentication.getPrincipal();
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setChat(chatRepository.findById(chatId).get());
        messageEntity.setUser(userEntity);
        messageEntity.setText(message);
        messageRepository.save(messageEntity);
        String sender_id = userEntity.getId();
        String routingKey = chatId;
        rabbitTemplate.convertAndSend(MAIN_EXCHANGE, routingKey, message, m -> {
            m.getMessageProperties().getHeaders().put("sender", sender_id);
            return m;
        });
        return "Message Sent Successfully.";
    }
}
