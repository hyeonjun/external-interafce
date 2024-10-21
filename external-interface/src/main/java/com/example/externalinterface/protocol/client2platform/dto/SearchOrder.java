package com.example.externalinterface.protocol.client2platform.dto;

import java.time.LocalDate;

public interface SearchOrder {

  String getTaxCode();
  LocalDate getFromDate();
  LocalDate getToDate();
  int getPageNumber();
  int getRowCount();
}
