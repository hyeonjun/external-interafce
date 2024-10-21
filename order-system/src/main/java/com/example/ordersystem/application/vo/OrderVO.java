package com.example.ordersystem.application.vo;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.order.domain.Order;
import com.example.ordersystem.application.order.domain.code.OrderStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderVO {

  private long orderId;
  private String clientOrderId;
  private LocalDateTime orderDateTime;
  private OrderStatus orderStatus;

  private long clientId;
  private String clientName;

  public OrderVO() {
  }

  @QueryProjection
  public OrderVO(long orderId, LocalDateTime orderDateTime, OrderStatus orderStatus, long clientId,
    String clientName) {
    this.orderId = orderId;
    this.orderDateTime = orderDateTime;
    this.orderStatus = orderStatus;
    this.clientId = clientId;
    this.clientName = clientName;
  }

  public static OrderVO valueOf(Order order) {
    OrderVO vo = new OrderVO();
    vo.setOrderId(order.getId());
    vo.setOrderDateTime(order.getOrderDateTime());
    vo.setOrderStatus(order.getOrderStatus());

    Client client = order.getClient();
    vo.setClientId(client.getId());
    vo.setClientName(client.getName());
    return vo;
  }
}
