package com.example.externalinterface.protocol.platform2client.humus.dto;

import com.example.externalinterface.protocol.platform2client.ClientDateConverter;

public abstract class HumusDTOAdapter implements ClientDateConverter {

  @Override
  public String getClientDateFormat() {
    return "yyyyMMdd";
  }
}
