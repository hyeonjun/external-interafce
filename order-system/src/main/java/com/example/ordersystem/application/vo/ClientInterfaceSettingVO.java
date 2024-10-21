package com.example.ordersystem.application.vo;

import com.example.ordersystem.application.client.domain.InterfaceSetting;
import com.example.ordersystem.application.client.domain.code.InterfaceType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientInterfaceSettingVO {

  private long interfaceId;
  private InterfaceType interfaceType;
  private String interfaceApiPath;
  private boolean enabled;

  public static ClientInterfaceSettingVO entityToVO(InterfaceSetting setting) {
    ClientInterfaceSettingVO vo = new ClientInterfaceSettingVO();
    vo.setInterfaceId(setting.getId());
    vo.setInterfaceType(setting.getInterfaceType());
    vo.setInterfaceApiPath(setting.getInterfaceApiPath());
    vo.setEnabled(setting.isEnabled());
    return vo;
  }
}
