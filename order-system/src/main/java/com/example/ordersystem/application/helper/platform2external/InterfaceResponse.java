package com.example.ordersystem.application.helper.platform2external;

import lombok.Getter;
import lombok.Setter;

@Setter
public class InterfaceResponse<T> {

  private T data;
  @Getter
  private String code;
  @Getter
  private String message;

  public T getData() {
    isValid();
    return data;
  }

  public void isValid() {
    if (!isSuccessCode()) {
      throw new RuntimeException("client interface exception");
    }
  }

  private boolean isSuccessCode() {
    return "0000".equals(code);
  }
}
