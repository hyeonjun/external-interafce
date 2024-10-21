package com.example.ordersystem.application.helper.platform2external.vo;

import com.example.ordersystem.application.order.domain.code.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientOrderVO {

  private Long clientOrderId;
  private OrderStatus orderStatus;
  private LocalDateTime orderDateTime;

}
