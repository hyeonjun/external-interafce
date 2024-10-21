package com.example.papercompany;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

  @Query(
    value = "select o.* from orders o " +
      "where o.order_no > :cursor " +
      "order by o.order_no asc " +
      "limit :limit",
  nativeQuery = true)
  List<OrderEntity> findAllByCursorLimitQuery(@Param("cursor") Long cursor, @Param("limit") Long limit);

}
