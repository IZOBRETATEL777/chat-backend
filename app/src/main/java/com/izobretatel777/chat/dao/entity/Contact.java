package com.izobretatel777.chat.dao.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Table(name = "contacts")
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class Contact {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "login", nullable = false, unique = true)
    String login;

    @Column(name = "name")
    String name;

    @Column(name = "surname")
    String surname;

    @Column(name = "phone_number")
    String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    User owner;
}
