package com.book.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.config.WebSecurityConfig;
import com.book.entity.User;
import com.book.exception.DuplicatedUserInfoException;
import com.book.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/authenticate")
	public User login(@Valid @RequestBody User userRequest) {
		User user = userService.validUsernameAndPassword(userRequest.getUsername(), userRequest.getPassword());
		if (user == null) {
			throw new DuplicatedUserInfoException(
					String.format("Username %s is already been used", userRequest.getUsername()));
		}
		return user;
	}

	@PostMapping("/signup")
	public User signUp(@Valid @RequestBody User userRequest) {
		if (userService.hasUserWithUsername(userRequest.getUsername())) {
			throw new DuplicatedUserInfoException(
					String.format("Username %s is already been used", userRequest.getUsername()));
		}
		if (userService.hasUserWithEmail(userRequest.getEmail())) {
			throw new DuplicatedUserInfoException(
					String.format("Email %s is already been used", userRequest.getEmail()));
		}

		User user = new User();
		user.setName(userRequest.getName());
		user.setUsername(userRequest.getUsername());
		user.setEmail(userRequest.getEmail());
		user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		user.setRole(WebSecurityConfig.USER);

		userService.saveUser(user);

		return user;
	}

}
