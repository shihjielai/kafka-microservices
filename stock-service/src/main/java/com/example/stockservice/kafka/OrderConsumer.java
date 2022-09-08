package com.example.stockservice.kafka;

import com.example.basedomains.dto.OrderEvent;
import com.example.stockservice.entity.OrderLog;
import com.example.stockservice.repository.OrderLogRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderConsumer {

    private OrderLogRepository orderLogRepository;

    @Autowired
    public void setOrderLogRepository(OrderLogRepository orderLogRepository) {
        this.orderLogRepository = orderLogRepository;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent orderEvent) {

        log.info("Order event received in stock service => {}", orderEvent);

        // save the order event into database
        OrderLog orderLog = new OrderLog();
        orderLog.setMessage(orderEvent.getMessage());
        orderLog.setStatus(orderEvent.getStatus());
        orderLog.setOrderId(orderEvent.getOrder().getOrderId());
        orderLog.setOrderName(orderEvent.getOrder().getName());
        orderLog.setOrderQuantity(orderEvent.getOrder().getQuantity());
        orderLog.setOrderPrice(orderEvent.getOrder().getPrice());
        orderLogRepository.save(orderLog);

        log.info("order event received => {}", orderEvent);
        log.info("order log saved => {}", orderLog);
    }
}
