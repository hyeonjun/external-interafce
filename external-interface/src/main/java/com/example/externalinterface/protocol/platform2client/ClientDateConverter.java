package com.example.externalinterface.protocol.platform2client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public interface ClientDateConverter {

  default String convertLocalDateToClientDateFormat(LocalDate localDate, String format) {
    return localDate.format(DateTimeFormatter.ofPattern(format));
  }

  default LocalDate convertClientDateFormatToLocalDate(String clientStringDate, DateTimeFormatter clientDateFormat) {
    DateTimeFormatter formatter = Optional.ofNullable(clientDateFormat)
      .orElse(DateTimeFormatter.BASIC_ISO_DATE);
    return StringUtils.hasText(clientStringDate) ? LocalDate.parse(clientStringDate, formatter) : null;
  }

  @JsonIgnore
  default String getClientDateFormat() {
    return "yyyy-MM-dd";
  }

}
