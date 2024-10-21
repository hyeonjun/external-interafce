package com.example.externalinterface.protocol.platform2client.humus.code;

import com.example.externalinterface.code.OrderStatus;
import com.example.externalinterface.protocol.platform2client.ConvertibleType;

public enum HumusOrderStatus implements ConvertibleType<String, OrderStatus> {

  HUMUS_PAYMENT_COMPLETED(OrderStatus.PAYMENT_COMPLETED, "0"),
  HUMUS_PREPARING_PRODUCT(OrderStatus.PREPARING_PRODUCT, "1"),
  HUMUS_DELIVERY_START(OrderStatus.DELIVERY_START, "2"),
  HUMUS_DELIVERY(OrderStatus.DELIVERY, "3"),
  HUMUS_DELIVERY_COMPLETED(OrderStatus.DELIVERY_COMPLETED, "4"),
  ;

  private final OrderStatus platformOrderStatus;
  private final String code;

  HumusOrderStatus(OrderStatus platformOrderStatus, String code) {
    this.platformOrderStatus = platformOrderStatus;
    this.code = code;
  }

  @Override
  public String getCode() {
    return code;
  }

  @Override
  public OrderStatus getMappingCode() {
    return platformOrderStatus;
  }
}
