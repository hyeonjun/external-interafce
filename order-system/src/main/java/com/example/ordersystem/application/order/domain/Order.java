package com.example.ordersystem.application.order.domain;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.common.domain.Base;
import com.example.ordersystem.application.order.domain.code.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
  name = "orders"
)
public class Order extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_id")
  private Long id;

  @Column(name = "client_order_id", nullable = false)
  private Long clientOrderId;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_status", nullable = false, length = 191)
  @Setter
  private OrderStatus orderStatus;

  @Column(name = "order_date_time", nullable = false)
  private LocalDateTime orderDateTime;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "client_id")
  @JsonBackReference
  private Client client;

}
