package com.examly.toystore.service;

import com.examly.toystore.model.Order;
import com.examly.toystore.repository.OrderRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(int page, int size) {
        return orderRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public List<Order> getOrdersByCustomerNameLike(String customerName) {
        return orderRepository.findByCustomerNameLike(customerName);
    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        return orderRepository.findById(id).map(order -> {
            order.setCustomerName(updatedOrder.getCustomerName());
            order.setOrderDate(updatedOrder.getOrderDate());
            order.setTotalAmount(updatedOrder.getTotalAmount());
            return orderRepository.save(order);
        }).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
