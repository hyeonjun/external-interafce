package com.example.externalinterface.protocol.platform2client.platform.dto;

import com.example.externalinterface.code.ClientType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Setter;

@Setter
public class PlatformCommonDTO implements PlatformCommon {

  @NotNull
  private Long clientId;

  @NotNull
  private ClientType clientType;
  @NotBlank
  private String clientServerUrl;
  @NotBlank
  private String clientApiPath;

  public PlatformCommonDTO() {
  }

  public PlatformCommonDTO(PlatformCommon platformCommonDto) {
    this.clientId = platformCommonDto.getClientId();
    this.clientType = platformCommonDto.getClientType();
    this.clientServerUrl = platformCommonDto.getClientServerUrl();
    this.clientApiPath = platformCommonDto.getClientApiPath();
  }

  @Override
  public Long getClientId() {
    return clientId;
  }

  @Override
  public ClientType getClientType() {
    return clientType;
  }

  @Override
  public String getUrl() {
    return clientServerUrl.concat(clientApiPath);
  }

  @Override
  public String getClientServerUrl() {
    return clientServerUrl;
  }

  @Override
  public String getClientApiPath() {
    return clientApiPath;
  }
}
