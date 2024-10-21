package com.example.ordersystem.application.order.domain.repository;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.order.domain.Order;
import com.example.ordersystem.application.order.service.dto.SearchOrderDTO;
import com.example.ordersystem.application.vo.OrderVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderRepositoryCustom {

  Page<OrderVO> findAll(SearchOrderDTO dto, Pageable pageable);

  List<Order> findAllNotDeliveredOrderByClient(Client client, long cursor, long limit);
}
