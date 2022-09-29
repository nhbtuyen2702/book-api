package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.book.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query(value = "SELECT COALESCE(MAX(ord.orderNum), 0) FROM Order ord")
	public int getMaxOrderNum();

	List<Order> findAllByOrderByOrderNum();

	List<Order> findByNameContainingOrderByOrderNum(String name);

}
