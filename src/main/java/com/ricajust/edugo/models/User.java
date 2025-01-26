package com.ricajust.edugo.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) // Ou SINGLE_TABLE, dependendo do que você quer
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String role;
}
