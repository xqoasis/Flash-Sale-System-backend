package com.skillup.domain.order;

import com.skillup.domain.order.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public OrderDomain creatOrder(OrderDomain orderDomain) {
        orderDomain.setCreateTime(LocalDateTime.now());
        orderDomain.setOrderStatus(OrderStatus.CREATED);
        orderRepository.createOrder(orderDomain);
        return orderDomain;

    }
}
