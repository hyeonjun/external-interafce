package com.example.papercompany.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderListVO extends CommonVO {

  private List<OrderVO> orderList;

  public OrderListVO() {
  }

  public OrderListVO(List<OrderVO> orderList) {
    super();
    this.orderList = orderList;
  }
}
