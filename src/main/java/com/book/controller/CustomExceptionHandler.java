package com.book.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.book.exception.BookNotFoundException;
import com.book.exception.DuplicatedUserInfoException;
import com.book.exception.UserNotFoundException;
import com.book.model.ErrorResponse;

@ControllerAdvice
public class CustomExceptionHandler {

	// Exception method for Internal Server Error.
	@ExceptionHandler(Exception.class)
	public ErrorResponse handleAllExceptions(Exception ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Server Error.", details);
		return error;
	}

	// Exception method for User Not Found with Status code 404.
	@ExceptionHandler(BookNotFoundException.class)
	public ErrorResponse handleBookNotFoundException(BookNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Error.", details);
		return error;
	}

	@ExceptionHandler(DuplicatedUserInfoException.class)
	public ErrorResponse handleDuplicatedUserInfoException(DuplicatedUserInfoException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Error.", details);
		return error;
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ErrorResponse handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		List<String> details = new ArrayList<>();
		details.add(ex.getLocalizedMessage());
		ErrorResponse error = new ErrorResponse("Error.", details);
		return error;
	}

	// Exception method for Invalid request body and Method Argument.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		List<String> details = new ArrayList<>();
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			details.add(error.getDefaultMessage());
		}
		ErrorResponse error = new ErrorResponse("Validation Failed.", details);
		return error;
	}
}