package com.example.ordersystem.application.vo.defaultvo;

import com.example.ordersystem.application.client.domain.Client;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultClientVO {

  protected long clientId;
  protected String name;
  protected String taxCode;

  public DefaultClientVO() {
  }

  public DefaultClientVO(Client client) {
    this.clientId = client.getId();
    this.name = client.getName();
    this.taxCode = client.getTaxCode();
  }
}
