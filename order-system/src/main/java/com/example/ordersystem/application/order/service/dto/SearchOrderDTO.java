package com.example.ordersystem.application.order.service.dto;

import com.example.ordersystem.application.common.dto.PagingDTO;
import com.example.ordersystem.application.order.domain.code.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SearchOrderDTO extends PagingDTO {

  private LocalDate orderDateFrom;
  private LocalDate orderDateTo;
  private List<OrderStatus> orderStatusConditions = new ArrayList<>();

  private Long clientId;
  private String clientTaxCode;
  private String clientName;

}
