package com.example.externalinterface.protocol.client2platform.dto.humus;

import com.example.externalinterface.protocol.client2platform.dto.SearchOrder;
import com.example.externalinterface.protocol.platform2client.humus.dto.HumusDTOAdapter;
import jakarta.validation.constraints.NotBlank;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Setter
public class HumusSearchOrderDTO extends HumusDTOAdapter implements SearchOrder {

  private int pageNumber = 0;
  private int rowCount = 20;

  @NotBlank
  private String taxCode;

  @NotBlank
  private String fromDate;
  @NotBlank
  private String toDate;

  @Override
  public String getTaxCode() {
    return taxCode;
  }

  @Override
  public LocalDate getFromDate() {
    return convertClientDateFormatToLocalDate(fromDate,
      DateTimeFormatter.ofPattern(getClientDateFormat()));
  }

  @Override
  public LocalDate getToDate() {
    return convertClientDateFormatToLocalDate(toDate,
      DateTimeFormatter.ofPattern(getClientDateFormat()));
  }

  @Override
  public int getPageNumber() {
    return pageNumber;
  }

  @Override
  public int getRowCount() {
    return rowCount;
  }
}
