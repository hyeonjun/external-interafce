package com.example.externalinterface.controller;

import com.example.externalinterface.protocol.platform2client.platform.dto.SearchClientOrderDTO;
import com.example.externalinterface.service.ClientServiceProxy;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
@RequiredArgsConstructor
public class PlatformToClientController {

  private final ClientServiceProxy serviceProxy;
  
  @PostMapping(value = "/current-orders", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getCurrentOrders(@RequestBody @Valid SearchClientOrderDTO dto) {
    return ResponseEntity.ok(serviceProxy.getCurrentOrders(dto));
  }
  
  @PostMapping(value = "/order-status", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getOrderStatus(@Valid @RequestBody SearchClientOrderDTO dto) {
    return ResponseEntity.ok(serviceProxy.getOrderStatus(dto));
  }
}
