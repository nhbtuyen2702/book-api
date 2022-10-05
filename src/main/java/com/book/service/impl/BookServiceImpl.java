package com.book.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.repository.BookRepository;
import com.book.service.BookService;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getBooks() {
		return bookRepository.findAllByOrderByTitle();
	}

	@Override
	public List<Book> getBooksContainingText(String text) {
		return bookRepository.findByIsbnContainingOrTitleContainingOrderByTitle(text, text);
	}

	@Override
	public Book findByIsbn(String isbn) {
		return bookRepository.findByIsbn(isbn);
	}

	@Override
	public Book createOrUpdateBook(Book bookReq) {
		Book book = bookRepository.findByIsbn(bookReq.getIsbn());
		if(book != null) {
			book.setIsbn(bookReq.getIsbn());
			book.setTitle(bookReq.getTitle());
			book.setPrice(bookReq.getPrice());
		}
		return book != null ? bookRepository.save(book) : bookRepository.save(bookReq);
	}

}
