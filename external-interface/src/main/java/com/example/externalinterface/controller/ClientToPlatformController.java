package com.example.externalinterface.controller;

import com.example.externalinterface.protocol.client2platform.dto.humus.HumusSearchOrderDTO;
import com.example.externalinterface.protocol.client2platform.dto.platform.PlatformSearchOrderDTO;
import com.example.externalinterface.service.platform.PlatformService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/platform")
@RequiredArgsConstructor
public class ClientToPlatformController {

  private final PlatformService platformService;

  @PostMapping(value = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity getOrders(@RequestBody @Valid HumusSearchOrderDTO dto) {
    return ResponseEntity.ok(platformService.searchOrders(dto));
  }
  
}
