package com.izobretatel777.chat.dao.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Table(name = "user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "login", nullable = false, unique = true)
    String login;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name = "phone_number")
    String phoneNumber;

    @Column(name = "OTP")
    String otp;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    boolean active = true;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(10) default 'USER'")
    String role = "USER";

    @OneToMany(mappedBy = "owner")
    List<Chat> chatList = new ArrayList<>();

    @OneToMany(mappedBy = "author")
    List<Message> messages = new ArrayList<>();

    @OneToMany(mappedBy = "owner")
    List<Contact> contacts = new ArrayList<>();

    @OneToMany(mappedBy = "userId")
    List<Contact> friends = new ArrayList<>();

    @ManyToMany(mappedBy = "users")
    List<Chat> chats = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "key_id", referencedColumnName = "id", nullable = false)
    Key key;
}
