package com.izobretatel777.chat.dao.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.*;
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
    @Email
    @NotBlank
    @Size(max = 255)
    String login;

    @Column(name = "password", nullable = false)
    @NotBlank
    @Size(min = 6, max = 255)
    String password;

    @Column(name = "name", columnDefinition = "varchar(30)")
    @Size(max = 30)
    String name;

    @Column(name = "surname", columnDefinition = "varchar(50)")
    @Size(max = 50)
    String surname;

    @Column(name = "phone_number", columnDefinition = "varchar(15)")
    @Pattern(regexp = "(^[0-9,\\-\\+]{9,15}$|^$)")
    String phoneNumber;

    @Column(name = "OTP")
    @Pattern(regexp = "^[0-9]{6}$")
    String otp;

    @Column(name = "active", nullable = false, columnDefinition = "boolean default true")
    boolean active = true;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(10) default 'USER'")
    String role = "USER";

    @OneToMany(mappedBy = "owner")
    List<Chat> chatList;

    @OneToMany(mappedBy = "author")
    List<Message> messages;

    @OneToMany(mappedBy = "owner")
    List<Contact> contacts;

    @OneToMany(mappedBy = "userId")
    List<Contact> friends;

    @ManyToMany(mappedBy = "users")
    List<Chat> chats;

    @OneToOne
    @JoinColumn(name = "key_id", referencedColumnName = "id", nullable = false)
    Key key;
}
