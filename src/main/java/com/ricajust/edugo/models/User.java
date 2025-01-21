package com.ricajust.edugo.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.*;

// Base User class
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	private String name;

	@Column(unique = true)
	private String email;

	private String password;

	private String role;
}
