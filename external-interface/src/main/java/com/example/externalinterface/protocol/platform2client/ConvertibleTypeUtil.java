package com.example.externalinterface.protocol.platform2client;

import java.util.EnumSet;
import java.util.List;

public class ConvertibleTypeUtil {

  public static <CLIENT_CODE extends Enum<CLIENT_CODE> & ConvertibleType, PLATFORM_TYPE> PLATFORM_TYPE convertToMappingType(
    Class<CLIENT_CODE> clientCodeClazz, Class<PLATFORM_TYPE> platformTypeClazz,
    String clientCode) {

    Object clientCodeType = findTypeByCode(clientCodeClazz,
      clientCode).getMappingCode();

    return platformTypeClazz.cast(clientCodeType);
  }

  public static <CLIENT_CODE extends Enum<CLIENT_CODE> & ConvertibleType> CLIENT_CODE findTypeByCode(
    Class<CLIENT_CODE> clientCodeClazz, String clientCode) {

    Object clientCodeType = EnumSet.allOf(clientCodeClazz).stream()
        .filter(v -> {
          if (v.getCode() instanceof List) {
            return ((List<?>) v.getCode()).contains(clientCode);
          }
          return clientCode.equals(v.getCode());
        })
        .findFirst()
        .orElseThrow(() -> new RuntimeException("Could not find client code " + clientCode));

    return clientCodeClazz.cast(clientCodeType);
  }
}
