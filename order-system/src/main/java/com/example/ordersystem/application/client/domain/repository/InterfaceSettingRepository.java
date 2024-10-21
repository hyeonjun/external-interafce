package com.example.ordersystem.application.client.domain.repository;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.client.domain.InterfaceSetting;
import com.example.ordersystem.application.client.domain.code.InterfaceType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InterfaceSettingRepository extends JpaRepository<InterfaceSetting, Long> {

  List<InterfaceSetting> findAllByClient(Client client);

  Optional<InterfaceSetting> findByInterfaceTypeAndClientAndEnabledTrue(InterfaceType interfaceType, Client client);

  List<InterfaceSetting> findAllByInterfaceTypeAndEnabledTrue(InterfaceType interfaceType);
}
