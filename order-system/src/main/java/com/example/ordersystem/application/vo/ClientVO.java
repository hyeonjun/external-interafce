package com.example.ordersystem.application.vo;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.vo.defaultvo.DefaultClientVO;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientVO extends DefaultClientVO {

  public ClientVO() {
  }

  public ClientVO(Client client) {
    super(client);
  }

  @QueryProjection
  public ClientVO(long clientId, String name, String taxCode) {
    this.clientId = clientId;
    this.name = name;
    this.taxCode = taxCode;
  }
}
