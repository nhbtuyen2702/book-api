package com.book.service;

import java.util.List;

import com.book.entity.User;

public interface UserService {

	List<User> getUsers();

	User getUserByUsername(String username);

	boolean hasUserWithUsername(String username);

	boolean hasUserWithEmail(String email);

	User saveUser(User user);

	void deleteUser(User user);

	User validUsernameAndPassword(String username, String password);

}
