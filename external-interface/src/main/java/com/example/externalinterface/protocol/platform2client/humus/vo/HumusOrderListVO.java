package com.example.externalinterface.protocol.platform2client.humus.vo;

import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrder;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderList;
import lombok.Setter;

import java.util.List;

@Setter
public class HumusOrderListVO extends HumusVOAdapter implements ClientOrderList {

  private List<HumusOrderVO> orderList;

  public HumusOrderListVO() {
  }

  @Override
  public List<? extends ClientOrder> getClientOrderList() {
    return orderList;
  }
}
