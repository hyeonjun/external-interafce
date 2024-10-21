package com.example.ordersystem.application.order.service;

import com.example.ordersystem.application.order.domain.Order;
import com.example.ordersystem.application.order.domain.repository.OrderRepository;
import com.example.ordersystem.application.order.service.dto.SearchOrderDTO;
import com.example.ordersystem.application.vo.OrderVO;
import com.example.ordersystem.application.vo.interfacevo.OrderListVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

  private final OrderRepository orderRepository;

  public Page<OrderVO> getOrders(SearchOrderDTO dto) {
    return orderRepository.findAll(dto, dto.getPageRequest());
  }

  public OrderVO getOrderDetail(Long id) {
    Order order = orderRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("order not found"));

    return OrderVO.valueOf(order);
  }

  public OrderListVO getOrdersByInterface(SearchOrderDTO dto) {
    List<OrderVO> orders = orderRepository.findAll(dto, dto.getPageRequest())
      .getContent();
    OrderListVO vo = new OrderListVO();
    vo.setOrders(orders);
    return vo;
  }

}
