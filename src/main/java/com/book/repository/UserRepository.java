package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

}
