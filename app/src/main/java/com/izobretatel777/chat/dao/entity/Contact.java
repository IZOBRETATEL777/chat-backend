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

    @ManyToOne
    @JoinColumn(name = "owner_id")
    User owner;

    @ManyToOne
    @JoinColumn(name = "user_id")
    User userId;

}
