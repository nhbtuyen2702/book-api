package com.book.service.impl;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.book.entity.Book;
import com.book.entity.Order;
import com.book.entity.OrderDetail;
import com.book.model.Cart;
import com.book.repository.OrderDetailRepository;
import com.book.repository.OrderRepository;
import com.book.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderDetailRepository orderDetailRepository;

	@Override
	public void createOrder(Cart cart) {
		int orderNum = orderRepository.getMaxOrderNum() + 1;

		Order order = new Order();
		order.setOrderNum(orderNum);
		order.setOrderDate(new Date());
		order.setTotalQuantity(cart.getTotalQuantity());
		order.setTotalAmount(cart.getTotalAmount());
		order.setName(cart.getName());

		orderRepository.save(order);

		List<Book> books = cart.getBooks();
		for (Book book : books) {
			OrderDetail detail = new OrderDetail();
			detail.setOrder(order);
			detail.setPrice(book.getPrice());
			detail.setQuantity(book.getQuantity());
			detail.setAmount(book.getAmount());
			detail.setBook(book);

			orderDetailRepository.save(detail);
		}

	}

	@Override
	public List<Order> getOrders() {
		return orderRepository.findAllByOrderByOrderNum();
	}

	@Override
	public List<Order> getOrdersContainingText(String text) {
		return orderRepository.findByNameContainingOrderByOrderNum(text);
	}

	@Override
	public List<OrderDetail> getOrderDetails(long orderId) {
		return orderDetailRepository.findAllOrderDetailsByOrderId(orderId);
	}

}
