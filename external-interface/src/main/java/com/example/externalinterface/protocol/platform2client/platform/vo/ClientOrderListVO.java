package com.example.externalinterface.protocol.platform2client.platform.vo;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Setter
public class ClientOrderListVO implements ClientOrderList {

  private List<ClientOrderVO> clientOrderList = new ArrayList<>();

  public ClientOrderListVO(ClientOrderList clientOrderList) {
    this.clientOrderList = clientOrderList.getClientOrderList()
      .stream()
      .map(ClientOrderVO::new).collect(Collectors.toList());
  }

  @Override
  public List<ClientOrderVO> getClientOrderList() {
    return clientOrderList;
  }
}
