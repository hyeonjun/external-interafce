package com.example.externalinterface.protocol.platform2client.humus.vo;

import com.example.externalinterface.code.OrderStatus;
import com.example.externalinterface.protocol.platform2client.ConvertibleTypeUtil;
import com.example.externalinterface.protocol.platform2client.humus.code.HumusOrderStatus;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrder;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
public class HumusOrderVO extends HumusVOAdapter implements ClientOrder {

  private Long orderNo;
  private String orderStatus;
  private LocalDateTime orderTime;

  @Override
  public Long getClientOrderId() {
    return orderNo;
  }

  @Override
  public OrderStatus getOrderStatus() {
    return ConvertibleTypeUtil.convertToMappingType(
      HumusOrderStatus.class, OrderStatus.class, orderStatus);
  }

  @Override
  public LocalDateTime getOrderTime() {
    return orderTime;
  }
}
