package com.example.externalinterface.protocol.client2platform.vo.humus;

import com.example.externalinterface.protocol.client2platform.vo.platform.OrderVO;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class HumusOrderVO {

  private Long orderNo;
  private String orderStatus;
  private LocalDateTime orderTime;

  public HumusOrderVO(OrderVO vo) {
    this.orderNo = vo.getClientOrderId() == null ? null : Long.valueOf(vo.getClientOrderId());
    this.orderStatus = vo.getOrderStatus().name();
    this.orderTime = vo.getOrderDateTime();
  }
}
