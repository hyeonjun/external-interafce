package com.example.ordersystem.application.client.service;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.client.domain.InterfaceSetting;
import com.example.ordersystem.application.client.domain.code.InterfaceType;
import com.example.ordersystem.application.client.domain.repository.ClientRepository;
import com.example.ordersystem.application.client.domain.repository.InterfaceSettingRepository;
import com.example.ordersystem.application.client.service.dto.CreateClientDTO;
import com.example.ordersystem.application.client.service.dto.SearchClientDTO;
import com.example.ordersystem.application.client.service.dto.UpdateClientDTO;
import com.example.ordersystem.application.client.service.dto.UpdateInterfaceSettingDTO;
import com.example.ordersystem.application.vo.ClientDetailVO;
import com.example.ordersystem.application.vo.ClientVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService {

  private final ClientRepository clientRepository;
  private final InterfaceSettingRepository interfaceSettingRepository;

  @Transactional
  public void create(CreateClientDTO dto) {
    clientRepository.findByTaxCode(dto.getTaxCode())
      .ifPresent(c -> {
        throw new RuntimeException("duplicate tax code");
      });

    Client client = Client.builder()
      .name(dto.getName())
      .taxCode(dto.getTaxCode())
      .serverRootPath(dto.getServerRootPath())
      .build();
    clientRepository.save(client);
  }

  @Transactional
  public void update(long clientId, UpdateClientDTO dto) {
    Client client = clientRepository.findById(clientId)
      .orElseThrow(() -> new RuntimeException("client not found"));

    client.setServerRootPath(dto.getServerRootPath());
    clientRepository.save(client);
  }

  @Transactional
  public void updateClientInterfaceSetting(long clientId, UpdateInterfaceSettingDTO dto) {
    Client client = clientRepository.findById(clientId)
      .orElseThrow(() -> new RuntimeException("client not found"));

    boolean isSavedClientRootPath = hasText(client.getServerRootPath());

    if (!isSavedClientRootPath) {
      boolean invalidInterface = dto.getInterfaceEnableList()
        .stream()
        .anyMatch(Boolean.TRUE::equals);
      if (invalidInterface) {
        throw new RuntimeException("not saved client root path");
      }
    }

    Map<InterfaceType, InterfaceSetting> clientIdInterfaceSettingMap = interfaceSettingRepository
      .findAllByClient(client)
      .stream()
      .collect(Collectors.toMap(
        InterfaceSetting::getInterfaceType,
        interfaceSetting -> interfaceSetting));

    for (int i=0; i<dto.getInterfaceTypeList().size(); i++) {
      InterfaceType interfaceType = dto.getInterfaceTypeList().get(i);
      InterfaceSetting interfaceSetting = clientIdInterfaceSettingMap.get(interfaceType);

      if (interfaceSetting == null) {
        interfaceSetting = InterfaceSetting
          .builder()
          .clientType(dto.getClientType())
          .interfaceType(interfaceType)
          .interfaceApiPath(dto.getInterfaceApiPathList().get(i))
          .enabled(dto.getInterfaceEnableList().get(i))
          .client(client)
          .build();
      } else {
        interfaceSetting.setInterfaceApiPath(dto.getInterfaceApiPathList().get(i));
        interfaceSetting.setEnabled(dto.getInterfaceEnableList().get(i));
      }

      interfaceSettingRepository.save(interfaceSetting);
    }
  }

  public Page<ClientVO> getClients(SearchClientDTO dto) {
    return clientRepository.findAll(dto, dto.getPageRequest());
  }

  public ClientDetailVO getClientDetail(Long id) {
    Client client = clientRepository.findById(id)
      .orElseThrow(() -> new RuntimeException("client not found"));

    List<InterfaceSetting> interfaceSettings = interfaceSettingRepository.findAllByClient(client);
    return ClientDetailVO.valueOf(client, interfaceSettings);
  }

}
