package com.example.externalinterface.protocol.platform2client.platform.dto;

import com.example.externalinterface.code.ClientType;

public interface PlatformCommon {

  Long getClientId();

  ClientType getClientType();

  String getUrl();

  String getClientServerUrl();

  String getClientApiPath();

}
