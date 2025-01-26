package com.ricajust.edugo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ricajust.edugo.models.User;
import com.ricajust.edugo.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return repository.save(user);
	}

	public boolean validateUser(String email, String rawPassword) {
		User user = repository.findByEmail(email);
		return user != null && passwordEncoder.matches(rawPassword, user.getPassword());
	}

	public User findByEmail(String email) {
		return repository.findByEmail(email);
	}
}
