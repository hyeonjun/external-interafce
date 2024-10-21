package com.example.ordersystem.application.order.domain.repository;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.order.domain.Order;
import com.example.ordersystem.application.order.domain.code.OrderStatus;
import com.example.ordersystem.application.order.service.dto.SearchOrderDTO;
import com.example.ordersystem.application.vo.OrderVO;
import com.example.ordersystem.application.vo.QOrderVO;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

import static com.example.ordersystem.application.client.domain.QClient.client;
import static com.example.ordersystem.application.order.domain.QOrder.order;
import static org.springframework.util.CollectionUtils.isEmpty;

public class OrderRepositoryImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom {

  private final JPAQueryFactory query;

  public OrderRepositoryImpl(EntityManager em) {
    super(Order.class);
    this.query = new JPAQueryFactory(em);
  }

  @Override
  public Page<OrderVO> findAll(SearchOrderDTO dto, Pageable pageable) {
    BooleanBuilder whereCondition = getWhereCondition(dto);

    JPAQuery<OrderVO> jpaQuery = query
      .select(selectOrderVO())
      .from(order)
      .innerJoin(order.client, client)
      .where(whereCondition)
      .orderBy(order.id.desc());

    List<OrderVO> result = getQuerydsl().applyPagination(pageable, jpaQuery).fetch();
    return new PageImpl<>(result, pageable, jpaQuery.fetchCount());
  }

  @Override
  public List<Order> findAllNotDeliveredOrderByClient(Client targetClient, long cursor, long limit) {
    return query.selectFrom(order)
      .innerJoin(order.client, client)
      .where(
        order.id.gt(cursor),
        client.eq(targetClient),
        order.orderStatus.in(OrderStatus.getBeforeDeliveryCompletedStatus()))
      .orderBy(order.id.asc())
      .limit(limit)
      .fetch();
  }

  private QOrderVO selectOrderVO() {
    return new QOrderVO(
      order.id,
      order.orderDateTime,
      order.orderStatus,
      client.id,
      client.name
    );
  }

  private BooleanBuilder getWhereCondition(SearchOrderDTO dto) {
    BooleanBuilder whereCondition = new BooleanBuilder();

    if (Objects.nonNull(dto.getOrderDateFrom())) {
      whereCondition.and(order.orderDateTime.goe(dto.getOrderDateFrom().atStartOfDay()));
    }

    if (Objects.nonNull(dto.getOrderDateTo())) {
      whereCondition.and(order.orderDateTime.lt(dto.getOrderDateTo().plusDays(1).atStartOfDay()));
    }

    if (!isEmpty(dto.getOrderStatusConditions())) {
      whereCondition.and(order.orderStatus.in(dto.getOrderStatusConditions()));
    }

    if (Objects.nonNull(dto.getClientName())) {
      whereCondition.and(client.name.contains(dto.getClientName()));
    }

    if (Objects.nonNull(dto.getClientId())) {
      whereCondition.and(client.id.eq(dto.getClientId()));
    }

    if (Objects.nonNull(dto.getClientTaxCode())) {
      whereCondition.and(client.taxCode.eq(dto.getClientTaxCode()));
    }

    return whereCondition;
  }
}
