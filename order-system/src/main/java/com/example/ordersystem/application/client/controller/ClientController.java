package com.example.ordersystem.application.client.controller;

import com.example.ordersystem.application.client.service.ClientService;
import com.example.ordersystem.application.client.service.dto.CreateClientDTO;
import com.example.ordersystem.application.client.service.dto.SearchClientDTO;
import com.example.ordersystem.application.client.service.dto.UpdateClientDTO;
import com.example.ordersystem.application.client.service.dto.UpdateInterfaceSettingDTO;
import com.example.ordersystem.application.vo.ClientDetailVO;
import com.example.ordersystem.application.vo.ClientVO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  @PostMapping
  public ResponseEntity<Void> create(@Valid @RequestBody CreateClientDTO dto) {
    clientService.create(dto);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<Void> update(
    @PathVariable Long id,
    @Valid @RequestBody UpdateClientDTO dto) {
    clientService.update(id, dto);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}/interface-settings")
  public ResponseEntity<Void> updateInterfaceSettings(@PathVariable Long id,
    @Valid @RequestBody UpdateInterfaceSettingDTO dto) {
    clientService.updateClientInterfaceSetting(id, dto);
    return ResponseEntity.ok().build();
  }

  @GetMapping
  public ResponseEntity<Page<ClientVO>> getClients(@Valid SearchClientDTO dto) {
    return ResponseEntity.ok(clientService.getClients(dto));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ClientDetailVO> getClient(@PathVariable Long id) {
    return ResponseEntity.ok(clientService.getClientDetail(id));
  }

}
