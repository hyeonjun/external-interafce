package com.example.externalinterface.service.humus;

import com.example.externalinterface.protocol.platform2client.humus.dto.HumusOrderDTO;
import com.example.externalinterface.protocol.platform2client.humus.vo.HumusOrderListVO;
import com.example.externalinterface.protocol.platform2client.humus.vo.HumusOrderVO;
import com.example.externalinterface.protocol.platform2client.platform.dto.SearchClientOrderDTO;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderListVO;
import com.example.externalinterface.protocol.platform2client.platform.vo.ClientOrderVO;
import org.springframework.stereotype.Service;

@Service
public class HumusService extends HumusServiceAdapter {

  @Override
  public ClientOrderListVO getCurrentOrders(SearchClientOrderDTO platformDto) {
    HumusOrderDTO humusOrderDTO = new HumusOrderDTO(platformDto);
    HumusOrderListVO humusOrderVO = sendDtoToClient(
      platformDto.getUrl(), humusOrderDTO, HumusOrderListVO.class);

    return new ClientOrderListVO(humusOrderVO);
  }

  @Override
  public ClientOrderVO getOrderStatus(SearchClientOrderDTO platformDto) {
    HumusOrderDTO humusOrderDTO = new HumusOrderDTO(platformDto);
    HumusOrderVO humusOrderVO = sendDtoToClient(
      platformDto.getUrl(), humusOrderDTO, HumusOrderVO.class);

    return new ClientOrderVO(humusOrderVO);
  }
}
