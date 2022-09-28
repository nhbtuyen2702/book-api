package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.User;
import com.book.exception.UserNotFoundException;
import com.book.service.UserService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/users/me")
	public User getCurrentUser() {
		UserDetails currentUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByUsername(currentUser.getUsername());
		if (user == null) {
			throw new UserNotFoundException(
					String.format("User with username %s not found", currentUser.getUsername()));
		}
		return user;
	}

	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getUsers();
	}

	@GetMapping("/users/{username}")
	public User getUser(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UserNotFoundException(String.format("User with username %s not found", username));
		}
		return user;
	}

	@DeleteMapping("/users/{username}")
	public void deleteUser(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
		if (user == null) {
			throw new UserNotFoundException(String.format("User with username %s not found", username));
		}
		userService.deleteUser(user);
	}

}