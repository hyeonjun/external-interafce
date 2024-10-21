package com.example.externalinterface.service;

import com.example.externalinterface.protocol.platform2client.platform.dto.PlatformCommonDTO;
import com.example.externalinterface.protocol.platform2client.platform.dto.SearchClientOrderDTO;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderListVO;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderVO;
import com.example.externalinterface.service.humus.HumusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.externalinterface.code.ClientType.HUMUS;

@Service
public class ClientServiceProxy {

  private Map serviceMap;

  public ClientServiceProxy(@Autowired HumusService humusService) {

    serviceMap = new HashMap();
    serviceMap.put(HUMUS, humusService);
  }

  private <T extends PlatformCommonDTO> ClientService selectService(T platformDto) {
    Object service = Optional.ofNullable(serviceMap.get(platformDto.getClientType()))
      .orElseThrow(() -> new RuntimeException("the interface is not supported"));

    return (ClientService) service;
  }

  public ClientOrderListVO getCurrentOrders(SearchClientOrderDTO platformDto) {
    return selectService(platformDto).getCurrentOrders(platformDto);
  }

  public ClientOrderVO getOrderStatus(SearchClientOrderDTO platformDto) {
    return selectService(platformDto).getOrderStatus(platformDto);
  }
}
