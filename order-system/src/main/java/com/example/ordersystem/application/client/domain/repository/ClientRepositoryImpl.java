package com.example.ordersystem.application.client.domain.repository;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.client.service.dto.SearchClientDTO;
import com.example.ordersystem.application.vo.ClientVO;
import com.example.ordersystem.application.vo.QClientVO;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.example.ordersystem.application.client.domain.QClient.client;

public class ClientRepositoryImpl extends QuerydslRepositorySupport implements ClientRepositoryCustom {

  private final JPAQueryFactory query;

  public ClientRepositoryImpl(EntityManager em) {
    super(Client.class);
    this.query = new JPAQueryFactory(em);
  }

  @Override
  public Page<ClientVO> findAll(SearchClientDTO dto, Pageable pageable) {

    JPAQuery<ClientVO> jpaQuery = query
      .select(selectClientVO())
      .from(client)
      .orderBy(client.id.desc());

    List<ClientVO> result = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(result, pageable, jpaQuery.fetchCount());
  }

  private QClientVO selectClientVO() {
    return new QClientVO(
      client.id,
      client.name,
      client.taxCode
    );
  }
}
