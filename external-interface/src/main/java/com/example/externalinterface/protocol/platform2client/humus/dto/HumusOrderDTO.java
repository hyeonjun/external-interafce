package com.example.externalinterface.protocol.platform2client.humus.dto;

import com.example.externalinterface.protocol.platform2client.platform.dto.SearchClientOrderDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HumusOrderDTO extends HumusDTOAdapter {

  private Long orderNo;

  public HumusOrderDTO(SearchClientOrderDTO dto) {
    this.orderNo = dto.getClientOrderNo();
  }
}
