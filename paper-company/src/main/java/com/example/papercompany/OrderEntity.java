package com.example.papercompany;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class OrderEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_no")
  private Long no;

  @CreationTimestamp
  protected LocalDateTime createdDateTime;
  @UpdateTimestamp
  protected LocalDateTime updatedDateTime;

  @Column(name = "order_status", nullable = false)
  private String orderStatus;

  @Column(name = "order_date_time", nullable = false)
  private LocalDateTime orderDateTime;
}
