package com.example.ordersystem.application.client.domain.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InterfaceType {

  CURRENT_ORDER("최신 주문 데이터","/current-orders"),
  ORDER_STATUS("주문 상태", "/order-status"),
  ;

  private final String description;
  private final String path;

  public String getApiPath() {
    return URL_PREFIX + path;
  }

  private static final String URL_PREFIX = "/api/client";
}
