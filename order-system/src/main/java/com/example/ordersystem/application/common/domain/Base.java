package com.example.ordersystem.application.common.domain;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class Base {

  @CreationTimestamp
  protected LocalDateTime createdDateTime;
  @UpdateTimestamp
  protected LocalDateTime updatedDateTime;

}
