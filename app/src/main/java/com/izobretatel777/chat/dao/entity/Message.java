package com.izobretatel777.chat.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.sql.Date;

@Table(name = "messages")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Message {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "content", nullable = false)
    String content;

    @Column(name = "delivered", nullable = false, columnDefinition = "boolean default false")
    boolean delivered = false;

    @Column(name = "creation_time", nullable = false, columnDefinition = "datetime default CURRENT_TIMESTAMP")
    Date creationTime;

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    User author;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    Chat chat;
}
