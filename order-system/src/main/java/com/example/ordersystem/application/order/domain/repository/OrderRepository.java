package com.example.ordersystem.application.order.domain.repository;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long>, OrderRepositoryCustom {

  Optional<Order> findTop1ByClientInOrderByIdDesc(Collection<Client> client);
}
