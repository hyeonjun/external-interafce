package com.example.ordersystem.application.scheduler;

import com.example.ordersystem.application.client.domain.Client;
import com.example.ordersystem.application.client.domain.InterfaceSetting;
import com.example.ordersystem.application.client.domain.code.InterfaceType;
import com.example.ordersystem.application.client.domain.repository.InterfaceSettingRepository;
import com.example.ordersystem.application.helper.ClientInterfaceHelper;
import com.example.ordersystem.application.helper.platform2external.dto.SearchClientOrderDTO;
import com.example.ordersystem.application.helper.platform2external.vo.ClientOrderVO;
import com.example.ordersystem.application.order.domain.Order;
import com.example.ordersystem.application.order.domain.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class SchedulerService {

  private final InterfaceSettingRepository interfaceSettingRepository;
  private final OrderRepository orderRepository;

  private final ClientInterfaceHelper clientInterfaceHelper;

  private final int TRANSACTION_CHUNK_SIZE = 30;

  // 매 1시간마다 새로운 주문 건 가져오기
//  @Scheduled(cron = "0 0 0/1 * * ?")
  @Scheduled(cron = "0 0/1 * * * ?")
  @Transactional
  public void scheduledSearchCurrentOrder() {
    InterfaceType interfaceType = InterfaceType.CURRENT_ORDER;
    Map<Client, InterfaceSetting> clientInterfaceSettingMap = interfaceSettingRepository
      .findAllByInterfaceTypeAndEnabledTrue(interfaceType).stream()
      .collect(Collectors.toMap(InterfaceSetting::getClient, setting -> setting));

    Map<Long, Long> clientCurrentOrderMap = orderRepository.findTop1ByClientInOrderByIdDesc(
      clientInterfaceSettingMap.keySet())
      .stream()
      .collect(Collectors.toMap(o -> o.getClient().getId(), Order::getClientOrderId));

    List<Order> orderForSave = new ArrayList<>();

    log.info("get current order scheduler start");
    for (Client client : clientInterfaceSettingMap.keySet()) {
      long cursor = clientCurrentOrderMap.getOrDefault(client.getId(), 0L);
      int orderSize = 0;

      do {
        List<ClientOrderVO> results = clientInterfaceHelper.getCurrentClientOrderInterface(
          SearchClientOrderDTO.valueOf(client, clientInterfaceSettingMap.get(client), cursor));
        orderSize = results.size();

        for (ClientOrderVO result : results) {
          Order order = Order
            .builder()
            .clientOrderId(result.getClientOrderId())
            .orderStatus(result.getOrderStatus())
            .orderDateTime(result.getOrderDateTime())
            .client(client)
            .build();
          orderForSave.add(order);
        }

        if (!orderForSave.isEmpty()) {
          orderRepository.saveAll(orderForSave);
          orderForSave.clear();
        }
      } while (orderSize == TRANSACTION_CHUNK_SIZE);
    }
  }

  // 저장된 주문 데이터 상태 변경
//  @Scheduled(cron = "0 0 6,9,12,15,18,21,0 * * ?")
  @Scheduled(cron = "30 0/1 * * * ?")
  @Transactional
  public void scheduledUpdateClientOrderStatus() {
    InterfaceType interfaceType = InterfaceType.ORDER_STATUS;
    Map<Client, InterfaceSetting> clientInterfaceSettingMap = interfaceSettingRepository
      .findAllByInterfaceTypeAndEnabledTrue(interfaceType).stream()
      .collect(Collectors.toMap(InterfaceSetting::getClient, setting -> setting));

    List<Order> orderForUpdate = new ArrayList<>();

    log.info("update order status scheduler start");
    for (Client client : clientInterfaceSettingMap.keySet()) {
      long cursor = 0L;
      int orderSize = 0;

      do {
        List<Order> notDeliveredOrders = orderRepository.findAllNotDeliveredOrderByClient(
          client, cursor, TRANSACTION_CHUNK_SIZE);
        orderSize = notDeliveredOrders.size();

        for (Order order : notDeliveredOrders) {
          cursor = order.getClientOrderId();

          ClientOrderVO result = clientInterfaceHelper.getClientOrderInterface(
            SearchClientOrderDTO.valueOf(client, clientInterfaceSettingMap.get(client), order.getClientOrderId()));

          if (!order.getOrderStatus().equals(result.getOrderStatus())) {
            order.setOrderStatus(result.getOrderStatus());
            orderForUpdate.add(order);
          }
        }

        if (!orderForUpdate.isEmpty()) {
          orderRepository.saveAll(orderForUpdate);
          orderForUpdate.clear();
        }

      } while (orderSize == TRANSACTION_CHUNK_SIZE);
    }
  }

}
