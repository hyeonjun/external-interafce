package com.example.ordersystem.application.helper.platform2external.dto.base;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.client.domain.InterfaceSetting;
import com.example.ordersystem.application.client.domain.code.ClientType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatformCommonDTO {

  @NotNull
  private Long clientId;

  @NotNull
  private ClientType clientType;
  @NotBlank
  private String clientServerUrl;
  @NotBlank
  private String clientApiPath;

  protected PlatformCommonDTO(Client client, InterfaceSetting interfaceSetting) {
    this.clientId = client.getId();
    this.clientServerUrl = client.getServerRootPath();
    this.clientType = interfaceSetting.getClientType();
    this.clientApiPath = interfaceSetting.getInterfaceApiPath();
  }

  public Long getClientUniqueId() {
    return clientId;
  }
}
