package com.example.ordersystem.application.helper.platform2external.dto;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.client.domain.InterfaceSetting;
import com.example.ordersystem.application.helper.platform2external.dto.base.PlatformCommonDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
public class SearchClientOrderDTO extends PlatformCommonDTO {

  @NotNull
  private Long clientOrderNo;

  private SearchClientOrderDTO(Client client, InterfaceSetting interfaceSetting) {
    super(client, interfaceSetting);
  }

  public static SearchClientOrderDTO valueOf(Client client, InterfaceSetting interfaceSetting,
    Long clientOrderNo) {
    SearchClientOrderDTO dto = new SearchClientOrderDTO(client, interfaceSetting);
    dto.setClientOrderNo(clientOrderNo);
    return dto;
  }

  public Long getClientOrderNo() {
    return clientOrderNo;
  }

  @Override
  public Long getClientUniqueId() {
    return clientOrderNo;
  }
}
