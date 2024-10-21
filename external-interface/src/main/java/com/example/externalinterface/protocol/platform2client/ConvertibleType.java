package com.example.externalinterface.protocol.platform2client;

public interface ConvertibleType<CODE, MAPPING_CODE> {

  CODE getCode();

  MAPPING_CODE getMappingCode();
}
