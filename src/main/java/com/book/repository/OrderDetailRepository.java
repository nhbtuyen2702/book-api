package com.book.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.book.entity.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

	@Query(value = "SELECT ord from OrderDetail ord WHERE ord.order.id = :id")
	List<OrderDetail> findAllOrderDetailsByOrderId(@Param("id") long id);

}
