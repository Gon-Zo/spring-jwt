package com.example.jwt.service;

import com.example.jwt.domain.Order;
import com.example.jwt.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final OrderRepository orderRepository;

  @Transactional(rollbackFor = Exception.class, readOnly = true)
  public List<Order> getOrderListByUser(Long userId) {
    return orderRepository.findByUser_IdOrderByOrderDateDesc(userId);
  }
}
