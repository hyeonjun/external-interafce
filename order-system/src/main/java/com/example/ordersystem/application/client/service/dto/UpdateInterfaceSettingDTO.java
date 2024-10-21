package com.example.ordersystem.application.client.service.dto;

import com.example.ordersystem.application.client.domain.code.ClientType;
import com.example.ordersystem.application.client.domain.code.InterfaceType;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdateInterfaceSettingDTO {

  private ClientType clientType;
  private List<InterfaceType> interfaceTypeList = new ArrayList<>();
  private List<String> interfaceApiPathList = new ArrayList<>();
  private List<Boolean> interfaceEnableList = new ArrayList<>();

}
