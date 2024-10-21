package com.example.ordersystem.application.client.service.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateClientDTO {

  // client info
  @NotBlank
  private String name;
  @NotBlank
  private String taxCode;
  private String serverRootPath;

}
