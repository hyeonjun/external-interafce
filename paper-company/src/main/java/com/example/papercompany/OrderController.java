package com.example.papercompany;

import com.example.papercompany.dto.CreateOrderDTO;
import com.example.papercompany.dto.SearchOrderDTO;
import com.example.papercompany.dto.SearchOrderInterfaceDTO;
import com.example.papercompany.dto.UpdateOrderDTO;
import com.example.papercompany.vo.OrderListVO;
import com.example.papercompany.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

  @Autowired
  private OrderRepository orderRepository;
  @Autowired
  private RestClient.Builder restClientBuilder;

  @PostMapping
  @Transactional
  public ResponseEntity<Void> createOrder(@RequestBody CreateOrderDTO dto) {
    OrderEntity order = new OrderEntity();
    order.setOrderStatus(dto.getOrderStatus());
    order.setOrderDateTime(LocalDateTime.now());
    orderRepository.save(order);
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}")
  @Transactional
  public ResponseEntity<Void> updateOrder(@PathVariable long id, @RequestBody UpdateOrderDTO dto) {
    OrderEntity order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("order not found"));
    order.setOrderStatus(dto.getOrderStatus());
    orderRepository.save(order);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/new-orders")
  public ResponseEntity<OrderListVO> getOrders(@RequestBody SearchOrderDTO dto) {
    List<OrderEntity> orderEntities = orderRepository.findAllByCursorLimitQuery(dto.getOrderNo(), 30L);
    List<OrderVO> orders = orderEntities.stream()
      .map(entity -> {
        OrderVO vo = new OrderVO();
        vo.setOrderNo(entity.getNo());
        vo.setOrderStatus(entity.getOrderStatus());
        vo.setOrderTime(entity.getOrderDateTime());
        return vo;
      }).toList();
    return ResponseEntity.ok(new OrderListVO(orders));
  }

  @PostMapping("/order-info")
  public ResponseEntity<OrderVO> getOrder(@RequestBody SearchOrderDTO dto) {
    OrderEntity order = orderRepository.findById(dto.getOrderNo())
      .orElseThrow(() -> new RuntimeException("order not found"));
    OrderVO vo = new OrderVO();
    vo.setOrderNo(order.getNo());
    vo.setOrderStatus(order.getOrderStatus());
    vo.setOrderTime(order.getOrderDateTime());
    return ResponseEntity.ok(vo);
  }

  @GetMapping("/interface")
  public ResponseEntity<OrderListVO> getOrderInterface(SearchOrderInterfaceDTO dto) {
    RestClient restClient = RestClient.builder().build();
    return restClient
      .post()
      .uri(URI.create("http://localhost:8081/api/platform/orders"))
      .accept(MediaType.APPLICATION_JSON)
      .body(dto)
      .retrieve()
      .toEntity(OrderListVO.class);
  }
}
