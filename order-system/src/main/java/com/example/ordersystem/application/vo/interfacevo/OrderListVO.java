package com.example.ordersystem.application.vo.interfacevo;

import com.example.ordersystem.application.vo.OrderVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderListVO {

  List<OrderVO> orders;
}
