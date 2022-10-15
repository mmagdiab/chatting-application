package com.iti.chatting.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "chat")
public class ChatEntity {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "chat_topic",length = 32)
    private String topic;
    @Column(name = "chat_password",length = 32)
    private String password;

    @ManyToMany
    @JoinTable(name = "user_chat", joinColumns = {@JoinColumn(name = "user_chat_chat_id")},
    inverseJoinColumns = {@JoinColumn(name = "user_chat_user_id")})
    List<UserEntity> user;

    @OneToMany(mappedBy = "chat")
    private List<MessageEntity> messages;

}
