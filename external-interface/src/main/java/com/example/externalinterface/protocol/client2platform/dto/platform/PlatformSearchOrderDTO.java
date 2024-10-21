package com.example.externalinterface.protocol.client2platform.dto.platform;

import com.example.externalinterface.code.OrderStatus;
import com.example.externalinterface.protocol.client2platform.dto.SearchOrder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class PlatformSearchOrderDTO extends PlatformPagingDTO {

  private LocalDate orderDateFrom;
  private LocalDate orderDateTo;
  private List<OrderStatus> orderStatusConditions = new ArrayList<>();

  private String clientId;
  private String clientTaxCode;
  private String clientName;

  public PlatformSearchOrderDTO() {
  }

  public PlatformSearchOrderDTO(SearchOrder searchOrder) {
    this.orderDateFrom = searchOrder.getFromDate();
    this.orderDateTo = searchOrder.getToDate();
    this.clientTaxCode = searchOrder.getTaxCode();
    this.pageNumber = searchOrder.getPageNumber();
    this.rowCount = searchOrder.getRowCount();
  }

}
