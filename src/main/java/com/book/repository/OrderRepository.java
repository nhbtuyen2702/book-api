package com.book.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.book.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
