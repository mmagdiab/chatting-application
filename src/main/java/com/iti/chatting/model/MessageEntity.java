package com.iti.chatting.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class MessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "message_datetime")
    private LocalDate messageTime;

    @Column(name = "message_text")
    private String text;

    @ManyToOne
    private UserEntity user;


    @ManyToOne
    private ChatEntity chat;


}
