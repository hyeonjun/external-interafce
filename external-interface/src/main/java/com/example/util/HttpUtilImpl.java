package com.example.util;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

import java.net.URI;

public class HttpUtilImpl implements HttpUtil {

  private final RestClient restClient;

  public HttpUtilImpl(RestClient restClient) {
    this.restClient = restClient;
  }

  @Override
  public <VO, DTO> VO httpPostReturnForJson(String url, DTO dto, Class<VO> returnClazz) {
    ResponseEntity<VO> responseEntity = restClient
      .post()
      .uri(URI.create(url))
      .accept(MediaType.APPLICATION_JSON)
      .body(dto)
      .retrieve()
      .toEntity(returnClazz);

    if (!responseEntity.getStatusCode().is2xxSuccessful()) {
      throw new RuntimeException("Network Error");
    }
    return responseEntity.getBody();
  }
}
