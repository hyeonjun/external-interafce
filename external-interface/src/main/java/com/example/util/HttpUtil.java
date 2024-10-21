package com.example.util;

public interface HttpUtil {

  <VO, DTO> VO httpPostReturnForJson(String url, DTO dto, Class<VO> responseType);

}
