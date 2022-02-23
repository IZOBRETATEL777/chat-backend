package com.izobretatel777.chat.dao.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name="users")
public class User {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="login")
    String login;

    @Column(name="password")
    String password;
}
