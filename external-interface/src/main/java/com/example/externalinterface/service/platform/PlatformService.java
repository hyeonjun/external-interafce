package com.example.externalinterface.service.platform;

import com.example.externalinterface.protocol.client2platform.dto.humus.HumusSearchOrderDTO;
import com.example.externalinterface.protocol.client2platform.dto.platform.PlatformSearchOrderDTO;
import com.example.externalinterface.protocol.client2platform.vo.humus.HumusOrderListVO;
import com.example.externalinterface.protocol.client2platform.vo.platform.OrderListVO;
import com.example.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlatformService {
  
  @Value("${platform.server.url}")
  private String platformServerUrl;

  @Autowired
  @Qualifier("defaultHttpUtil")
  private HttpUtil httpUtil;

  private final static String SEARCH_PLATFORM_ORDERS_PATH = "/api/orders/by-interface";

  public HumusOrderListVO searchOrders(HumusSearchOrderDTO dto) {

    PlatformSearchOrderDTO platformDto = new PlatformSearchOrderDTO(dto);
    String platformFullPath = platformServerUrl.concat(SEARCH_PLATFORM_ORDERS_PATH);
    OrderListVO vo = httpUtil.httpPostReturnForJson(platformFullPath, platformDto, OrderListVO.class);
    return new HumusOrderListVO(vo);
  }
  
}
