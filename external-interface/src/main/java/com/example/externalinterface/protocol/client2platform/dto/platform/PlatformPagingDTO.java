package com.example.externalinterface.protocol.client2platform.dto.platform;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlatformPagingDTO {

  protected int pageNumber = 0;
  protected int rowCount = 20;

}
