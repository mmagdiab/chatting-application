package com.iti.chatting.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class MessageEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @Column(name = "message_datetime")
    private LocalDate messageTime;

    @Column(name = "message_text")
    private String text;

    @ManyToOne
    private UserEntity user;


    @ManyToOne
    private ChatEntity chat;


}
