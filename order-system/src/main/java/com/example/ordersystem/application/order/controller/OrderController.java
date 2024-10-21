package com.example.ordersystem.application.order.controller;

import com.example.ordersystem.application.order.service.OrderService;
import com.example.ordersystem.application.order.service.dto.SearchOrderDTO;
import com.example.ordersystem.application.vo.OrderVO;
import com.example.ordersystem.application.vo.interfacevo.OrderListVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService;

  @GetMapping
  public ResponseEntity<Page<OrderVO>> getOrders(@Valid SearchOrderDTO dto) {
    return ResponseEntity.ok(orderService.getOrders(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<OrderVO> getOrderDetail(@PathVariable Long id) {
    return ResponseEntity.ok(orderService.getOrderDetail(id));
  }

  @PostMapping("by-interface")
  public ResponseEntity<OrderListVO> getOrdersByInterface(@Valid @RequestBody SearchOrderDTO dto) {
    return ResponseEntity.ok(orderService.getOrdersByInterface(dto));
  }
}
