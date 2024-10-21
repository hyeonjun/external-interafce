package com.example.externalinterface.protocol.client2platform.vo.platform;

import com.example.externalinterface.code.OrderStatus;
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
}
