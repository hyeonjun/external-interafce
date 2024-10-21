package com.example.ordersystem.application.client.domain;

import com.example.ordersystem.application.client.domain.code.ClientType;
import com.example.ordersystem.application.common.domain.Base;
import com.example.ordersystem.application.client.domain.code.InterfaceType;
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
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(
  name = "client_interface_setting",
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"interface_type", "client_id"})}
)
public class InterfaceSetting extends Base {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "inerface_setting_id")
  private Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "client_type", nullable = false, length = 191)
  private ClientType clientType;

  @Enumerated(EnumType.STRING)
  @Column(name = "interface_type", nullable = false, length = 191)
  private InterfaceType interfaceType;

  @Setter
  private String interfaceApiPath;
  @Setter
  private boolean enabled;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "client_id", nullable = false, referencedColumnName = "client_id")
  @JsonBackReference
  private Client client;

}
