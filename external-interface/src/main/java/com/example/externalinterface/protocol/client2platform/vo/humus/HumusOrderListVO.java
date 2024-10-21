package com.example.externalinterface.protocol.client2platform.vo.humus;

import com.example.externalinterface.protocol.client2platform.vo.platform.OrderListVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HumusOrderListVO {

  List<HumusOrderVO> orderList;

  public HumusOrderListVO(OrderListVO vo) {
    this.orderList = vo.getOrders().stream()
      .map(HumusOrderVO::new)
      .toList();
  }
}
