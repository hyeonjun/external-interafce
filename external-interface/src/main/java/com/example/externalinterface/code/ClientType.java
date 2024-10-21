package com.example.externalinterface.code;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ClientType {

  HUMUS("휴머스"),
  ;

  private final String name;

}
