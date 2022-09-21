package com.book.service;

import java.util.List;

import com.book.entity.Book;

public interface BookService {

	List<Book> getBooks();

	List<Book> getBooksContainingText(String text);

	Book findByIsbn(String isbn);

	Book saveBook(Book book);

	void deleteBook(Book book);

}
