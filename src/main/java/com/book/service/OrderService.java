package com.book.service;

import java.util.List;

import com.book.entity.Order;
import com.book.entity.OrderDetail;
import com.book.model.Cart;

public interface OrderService {

	void createOrder(Cart cart);

	List<Order> getOrders();

	List<Order> getOrdersContainingText(String text);

	List<OrderDetail> getOrderDetails(long orderId);

}
