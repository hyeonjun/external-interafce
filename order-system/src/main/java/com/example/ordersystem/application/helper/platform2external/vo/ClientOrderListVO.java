package com.example.ordersystem.application.helper.platform2external.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ClientOrderListVO {

  private List<ClientOrderVO> clientOrderList = new ArrayList<>();

}
