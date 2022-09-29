package com.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.entity.Order;
import com.book.entity.OrderDetail;
import com.book.model.Cart;
import com.book.service.OrderService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/orders/checkout")
	public void createOrder(@RequestBody Cart cart) {
		orderService.createOrder(cart);
		System.out.println(cart);
	}

	@GetMapping("/orders")
	public List<Order> getAllOrders(@RequestParam(value = "text", required = false) String text) {
		List<Order> orders = (text == null) ? orderService.getOrders() : orderService.getOrdersContainingText(text);
		return orders;
	}

	@GetMapping("/orders/{orderId}")
	public List<OrderDetail> getOrderDetails(@PathVariable(value = "orderId") long orderId) {
		List<OrderDetail> orderDetails = orderService.getOrderDetails(orderId);
		return orderDetails;
	}

}