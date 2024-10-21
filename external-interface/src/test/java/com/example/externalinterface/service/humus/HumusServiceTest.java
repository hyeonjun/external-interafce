package com.example.externalinterface.service.humus;

import com.example.externalinterface.code.ClientType;
import com.example.externalinterface.code.OrderStatus;
import com.example.externalinterface.code.RestClientConfig;
import com.example.externalinterface.config.ObjectMapperConfig;
import com.example.externalinterface.protocol.platform2client.humus.dto.HumusOrderDTO;
import com.example.externalinterface.protocol.platform2client.humus.vo.HumusOrderListVO;
import com.example.externalinterface.protocol.platform2client.humus.vo.HumusOrderVO;
import com.example.externalinterface.protocol.platform2client.platform.dto.SearchClientOrderDTO;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderListVO;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderVO;
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
@Import({RestClientConfig.class, ObjectMapperConfig.class, HumusService.class})
public class HumusServiceTest {

  @Autowired
  private HumusService humusService;
  @Autowired
  private ObjectMapper objectMapper;
  @Autowired
  private MockRestServiceServer mockServer;

  private final SearchClientOrderDTO platformDto = new SearchClientOrderDTO();

  public HumusServiceTest() {
    platformDto.setClientId(1L);
    platformDto.setClientType(ClientType.HUMUS);
    platformDto.setClientServerUrl("http://localhost:9090");
    platformDto.setClientOrderNo(1L);
  }

  @Test
  void 최근_주문_목록_조회() throws JsonProcessingException {
    platformDto.setClientApiPath("/current-orders");

    HumusOrderDTO humusOrderDTO = new HumusOrderDTO(platformDto);
    String requestJson = objectMapper.writeValueAsString(humusOrderDTO);

    HumusOrderVO orderVO = new HumusOrderVO();
    orderVO.setOrderNo(humusOrderDTO.getOrderNo());
    orderVO.setOrderStatus("0");
    orderVO.setOrderTime(LocalDateTime.now());

    HumusOrderListVO orderListVo = new HumusOrderListVO();
    orderListVo.setResponseCode("0000");
    orderListVo.setResponseMessage("success");
    orderListVo.setOrderList(List.of(orderVO));

    String responseJson = objectMapper.writeValueAsString(orderListVo);

    String requestUrl = platformDto.getUrl();
    assertEquals("http://localhost:9090/current-orders", requestUrl);

    mockServer
      .expect(requestTo(URI.create(requestUrl)))
      .andExpect(method(HttpMethod.POST))
      .andExpect(content().string(requestJson))
      .andRespond(withSuccess(responseJson, MediaType.APPLICATION_JSON));

    ClientOrderListVO vo = humusService.getCurrentOrders(platformDto);
    System.out.println(objectMapper.writeValueAsString(vo));

    List<ClientOrderVO> clientOrderList = vo.getClientOrderList();
    assertEquals(1, clientOrderList.size());

    ClientOrderVO clientOrderVO = clientOrderList.get(0);
    assertEquals(1L, clientOrderVO.getClientOrderId());
    assertEquals(OrderStatus.PAYMENT_COMPLETED, clientOrderVO.getOrderStatus());
  }

  @Test
  void 주문_상태_조회() throws JsonProcessingException {
    platformDto.setClientApiPath("/order-status");

    HumusOrderDTO humusOrderDTO = new HumusOrderDTO(platformDto);
    String requestJson = objectMapper.writeValueAsString(humusOrderDTO);

    HumusOrderVO orderVO = new HumusOrderVO();
    orderVO.setResponseCode("0000");
    orderVO.setResponseMessage("success");
    orderVO.setOrderNo(humusOrderDTO.getOrderNo());
    orderVO.setOrderStatus("1");
    orderVO.setOrderTime(LocalDateTime.now());

    String responseJson = objectMapper.writeValueAsString(orderVO);

    String requestUrl = platformDto.getUrl();
    assertEquals("http://localhost:9090/order-status", requestUrl);

    mockServer
      .expect(requestTo(URI.create(requestUrl)))
      .andExpect(method(HttpMethod.POST))
      .andExpect(content().string(requestJson))
      .andRespond(withSuccess(responseJson, MediaType.APPLICATION_JSON));

    ClientOrderVO vo = humusService.getOrderStatus(platformDto);
    System.out.println(objectMapper.writeValueAsString(vo));
    assertEquals(1L, vo.getClientOrderId());
    assertEquals(OrderStatus.PREPARING_PRODUCT, vo.getOrderStatus());
  }

}
