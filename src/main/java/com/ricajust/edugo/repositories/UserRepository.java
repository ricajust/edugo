package com.ricajust.edugo.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ricajust.edugo.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
	User findByEmail(String email);
}
