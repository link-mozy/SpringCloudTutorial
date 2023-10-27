package com.dream.orderservice.service;

import com.dream.orderservice.dto.OrderDto;
import com.dream.orderservice.jpa.OrderEntity;

public interface OrderService {
    OrderDto crateOrder(OrderDto orderDetails);
    OrderDto getOrderByOrderId(String orderId);
    Iterable<OrderEntity> getOrdersByUserId(String userId);
}
