package com.example.ordersystem.application.client.domain.repository;

import com.example.ordersystem.application.client.service.dto.SearchClientDTO;
import com.example.ordersystem.application.vo.ClientVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientRepositoryCustom {

  Page<ClientVO> findAll(SearchClientDTO dto, Pageable pageable);

}
