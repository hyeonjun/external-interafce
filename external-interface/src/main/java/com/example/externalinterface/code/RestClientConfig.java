package com.example.externalinterface.code;

import com.example.util.HttpUtil;
import com.example.util.HttpUtilImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

  private static final String DEFAULT_REST_CLIENT = "defaultRestClient";
  private static final String DEFAULT_HTTP_UTIL = "defaultHttpUtil";

  @Bean(name = DEFAULT_REST_CLIENT)
  public RestClient restClient(RestClient.Builder builder) {
    return builder.build();
  }

  @Bean(DEFAULT_HTTP_UTIL)
  public HttpUtil defaultHttpUtil(@Qualifier(DEFAULT_REST_CLIENT) RestClient restClient) {
    return new HttpUtilImpl(restClient);
  }

}
