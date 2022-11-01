package com.petcare.backend.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
    public List<User> getUsers() {
		List<User> users = new ArrayList<User>();
		userRepository.findAll().forEach(user -> users.add(user));
		return users;
	}


	public User getUserById(int id) {
		return userRepository.findById(id).get();
	}

	public void saveOrUpdate(User user) {
		userRepository.save(user);
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}
}