package com.example.ordersystem.application.order.domain.code;

import java.util.EnumSet;
import java.util.Set;

public enum OrderStatus {

  PAYMENT_COMPLETED,
  PREPARING_PRODUCT,
  DELIVERY_START,
  DELIVERY,
  DELIVERY_COMPLETED;

  private static final Set<OrderStatus> BEFORE_DELIVERY_COMPLETED = EnumSet.of(
    PAYMENT_COMPLETED, PREPARING_PRODUCT, DELIVERY_START, DELIVERY);

  public static Set<OrderStatus> getBeforeDeliveryCompletedStatus() {
    return BEFORE_DELIVERY_COMPLETED;
  }

}
