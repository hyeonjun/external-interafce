package com.example.externalinterface.protocol.platform2client.platform.vo;

import com.example.externalinterface.code.OrderStatus;

import java.time.LocalDateTime;

public interface ClientOrder {

  Long getClientOrderId();
  OrderStatus getOrderStatus();
  LocalDateTime getOrderTime();

}
