package com.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.service.BookService;
import com.book.service.UserService;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PublicController {

	@Autowired
	private UserService userService;

	@Autowired
	private BookService bookService;

	@GetMapping("/numberOfUsers")
	public Integer getNumberOfUsers() {
		return userService.getUsers().size();
	}

	@GetMapping("/numberOfBooks")
	public Integer getNumberOfBooks() {
		return bookService.getBooks().size();
	}

}
