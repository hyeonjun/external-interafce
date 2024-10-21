package com.example.papercompany.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderVO extends CommonVO {

  private Long orderNo;
  private String orderStatus;
  private LocalDateTime orderTime;

  public OrderVO() {
    super();
  }
}
