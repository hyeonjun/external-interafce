package com.example.ordersystem.application.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class PagingDTO {

  protected int pageNumber = 0;
  protected int rowCount = 20;

  public Pageable getPageRequest() {
    return PageRequest.of(this.pageNumber, this.rowCount);
  }
}
