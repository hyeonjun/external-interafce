package com.example.externalinterface.service.humus;

import com.example.externalinterface.protocol.platform2client.ClientVO;
import com.example.externalinterface.service.ClientService;
import com.example.util.HttpUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class HumusServiceAdapter implements ClientService {

  @Autowired
  @Qualifier("defaultHttpUtil")
  protected HttpUtil httpUtil;
  @Autowired
  protected ObjectMapper objectMapper;

  protected <VO extends ClientVO, DTO> VO sendDtoToClient(String url, DTO dto, Class<VO> voClass) {

    VO vo = httpUtil.httpPostReturnForJson(url, dto, voClass);

    vo.preHandle();
    vo.checkValidation();
    vo.postHandle();

    return voClass.cast(vo);
  }

}
