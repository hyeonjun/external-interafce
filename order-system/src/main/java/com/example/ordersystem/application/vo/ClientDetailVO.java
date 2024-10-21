package com.example.ordersystem.application.vo;

import static org.springframework.util.CollectionUtils.isEmpty;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.client.domain.InterfaceSetting;
import com.example.ordersystem.application.vo.defaultvo.DefaultClientVO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDetailVO extends DefaultClientVO {

  private String serverRootPath;
  private List<ClientInterfaceSettingVO> interfaceList;

  public ClientDetailVO() {
  }

  public ClientDetailVO(Client client) {
    super(client);
  }

  public static ClientDetailVO valueOf(Client client, List<InterfaceSetting> interfaceList) {
    ClientDetailVO vo = new ClientDetailVO(client);
    vo.setServerRootPath(client.getServerRootPath());

    if (!isEmpty(interfaceList)) {
      List<ClientInterfaceSettingVO> list = interfaceList
        .stream()
        .map(ClientInterfaceSettingVO::entityToVO)
        .toList();
      vo.setInterfaceList(list);
    }

    return vo;
  }

}
