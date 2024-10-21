package com.example.papercompany.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommonVO {

  private String responseCode;
  private String responseMessage;

  public CommonVO() {
    this.responseCode = "0000";
    this.responseMessage = "success";
  }
}
