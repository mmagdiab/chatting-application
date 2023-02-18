package com.iti.chatting.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "message")
public class MessageEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "message_id")
    private String id;

    @Column(name = "message_datetime")
    private LocalDate messageTime;

    @Column(name = "message_text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "message_user_id")
    private UserEntity user;


    @ManyToOne
    @JoinColumn(name = "message_chat_id")
    private ChatEntity chat;


}
