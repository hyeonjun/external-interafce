package com.example.externalinterface.protocol.platform2client.platform.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchClientOrderDTO extends PlatformCommonDTO {

  @NotNull
  private Long clientOrderNo;
}
