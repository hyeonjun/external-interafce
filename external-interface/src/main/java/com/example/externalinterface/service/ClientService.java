package com.example.externalinterface.service;

import com.example.externalinterface.protocol.platform2client.platform.dto.SearchClientOrderDTO;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderListVO;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderVO;

public interface ClientService {

  /**
   * 01. 최근 주문 정보 조회
   * @param platformDto
   * @return
   */
  default ClientOrderListVO getCurrentOrders(SearchClientOrderDTO platformDto) {
    throw new RuntimeException("the interface is not supported");
  }

  /**
   * 02. 주문 상태 조회
   * @param platformDto
   * @return
   */
  default ClientOrderVO getOrderStatus(SearchClientOrderDTO platformDto) {
    throw new RuntimeException("the interface is not supported");
  }

}
