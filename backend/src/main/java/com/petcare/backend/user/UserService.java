package com.petcare.backend.user;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    public List<User> getUsers() {
		return List.of(new User(1L, "Braydon Hughes", "bhughe2@ilstu.edu", "passwordsux", 1234567890));
	}
}
