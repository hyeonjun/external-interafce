package com.example.externalinterface.service.platform;

import com.example.externalinterface.code.OrderStatus;
import com.example.externalinterface.code.RestClientConfig;
import com.example.externalinterface.config.ObjectMapperConfig;
import com.example.externalinterface.protocol.client2platform.dto.humus.HumusSearchOrderDTO;
import com.example.externalinterface.protocol.client2platform.dto.platform.PlatformSearchOrderDTO;
import com.example.externalinterface.protocol.client2platform.vo.humus.HumusOrderListVO;
import com.example.externalinterface.protocol.client2platform.vo.humus.HumusOrderVO;
import com.example.externalinterface.protocol.client2platform.vo.platform.OrderListVO;
import com.example.externalinterface.protocol.client2platform.vo.platform.OrderVO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest
@Import({RestClientConfig.class, ObjectMapperConfig.class, PlatformService.class})
public class PlatformServiceTest {

  @Autowired
  private PlatformService platformService;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockRestServiceServer mockServer;

  @Test
  void 주문_목록_조회() throws JsonProcessingException {
    HumusSearchOrderDTO humusDto = new HumusSearchOrderDTO();
    humusDto.setPageNumber(0);
    humusDto.setRowCount(20);
    humusDto.setTaxCode("123123");
    humusDto.setFromDate("20241001");
    humusDto.setToDate("20241031");

    PlatformSearchOrderDTO dto = new PlatformSearchOrderDTO(humusDto);
    String requestJson = objectMapper.writeValueAsString(dto);

    OrderListVO vo = new OrderListVO();

    OrderVO orderVO = new OrderVO();
    orderVO.setOrderId(1L);
    orderVO.setClientOrderId("1");
    orderVO.setOrderDateTime(LocalDateTime.now());
    orderVO.setOrderStatus(OrderStatus.PAYMENT_COMPLETED);

    vo.setOrders(List.of(orderVO));
    String responseJson = objectMapper.writeValueAsString(vo);

    String requestUrl = "http://127.0.0.1:8080/api/orders/by-interface";
    mockServer
      .expect(requestTo(URI.create(requestUrl)))
      .andExpect(method(HttpMethod.POST))
      .andExpect(content().string(requestJson))
      .andRespond(withSuccess(responseJson, MediaType.APPLICATION_JSON));

    HumusOrderListVO orderListVO = platformService.searchOrders(humusDto);
    System.out.println(objectMapper.writeValueAsString(orderListVO));

    List<HumusOrderVO> orders = orderListVO.getOrderList();
    assertEquals(1, orders.size());
  }


}
