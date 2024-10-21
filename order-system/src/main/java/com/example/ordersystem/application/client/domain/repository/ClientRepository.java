package com.example.ordersystem.application.client.domain.repository;

import com.example.ordersystem.application.client.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {

  Optional<Client> findByTaxCode(String taxCode);

}
