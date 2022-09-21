package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.Book;

public interface BookRepository extends JpaRepository<Book, String> {

	List<Book> findAllByOrderByTitle();

	List<Book> findByIsbnContainingOrTitleContainingOrderByTitle(String isbn, String title);

	Book findByIsbn(String isbn);

}
