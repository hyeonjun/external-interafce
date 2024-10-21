package com.example.ordersystem.application.helper;

import com.example.ordersystem.application.client.domain.code.InterfaceType;
import com.example.ordersystem.application.helper.platform2external.dto.SearchClientOrderDTO;
import com.example.ordersystem.application.helper.platform2external.dto.base.PlatformCommonDTO;
import com.example.ordersystem.application.helper.platform2external.vo.ClientOrderListVO;
import com.example.ordersystem.application.helper.platform2external.vo.ClientOrderVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientInterfaceHelper {

  private final RestClient restClient;

  @Value("${external.interface.service.path}")
  private String EXTERNAL_SERVER_URL;

  // 최근 주문 조회
  public List<ClientOrderVO> getCurrentClientOrderInterface(SearchClientOrderDTO dto) {
    ClientOrderListVO result = postInterface(dto, InterfaceType.CURRENT_ORDER, ClientOrderListVO.class);

    return result != null ? result.getClientOrderList() : Collections.emptyList();
  }

  // 저장된 주문 건 상태 갱신
  public ClientOrderVO getClientOrderInterface(SearchClientOrderDTO dto) {
    return postInterface(dto, InterfaceType.ORDER_STATUS, ClientOrderVO.class);
  }

  public <T extends PlatformCommonDTO, V> V postInterface(T dto,
    InterfaceType interfaceType, Class<V> returnClazz) {
    try {
      ResponseEntity<V> response = restClient
        .post()
        .uri(getUrl(interfaceType))
        .accept(MediaType.APPLICATION_JSON)
        .body(dto)
        .retrieve()
        .toEntity(returnClazz);

      return response.getBody();
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new RuntimeException("interface not available");
    }
  }

  private URI getUrl(InterfaceType interfaceType) {
    String fullPath = EXTERNAL_SERVER_URL + interfaceType.getApiPath();
    return URI.create(fullPath);
  }
}
