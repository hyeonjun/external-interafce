package com.example.ordersystem.application.order.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SearchOrderByClientInterfaceDTO {

  private Long clientId;
  private LocalDate orderDateFrom;
  private LocalDate orderDateTo;


}
