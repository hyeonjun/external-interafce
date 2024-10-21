package com.example.externalinterface.protocol.platform2client.platform.vo;

import com.example.externalinterface.code.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class ClientOrderVO implements ClientOrder {

  private Long clientOrderId;
  private OrderStatus orderStatus;
  private LocalDateTime orderDateTime;

  public ClientOrderVO() {
  }

  public ClientOrderVO(ClientOrder clientOrder) {
    this.clientOrderId = clientOrder.getClientOrderId();
    this.orderStatus = clientOrder.getOrderStatus();
    this.orderDateTime = clientOrder.getOrderTime();
  }


  @Override
  public Long getClientOrderId() {
    return clientOrderId;
  }

  @Override
  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  @Override
  public LocalDateTime getOrderTime() {
    return orderDateTime;
  }
}
