package com.example.externalinterface.protocol.platform2client.humus.vo;

import com.example.externalinterface.protocol.platform2client.ClientVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class HumusVOAdapter implements ClientVO {

  protected String responseCode;
  protected String responseMessage;

  public HumusVOAdapter() {
  }

  @Override
  public void checkValidation() throws RuntimeException {
    if (!"0000".equals(responseCode)) {
      throw new RuntimeException("unknown response code: " + responseCode);
    }
  }
}
