package com.example.orderservice.kafka;

import com.example.basedomains.dto.OrderEvent;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class OrderProducer {

    private NewTopic topic;
    private KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @Autowired
    public void setTopic(NewTopic topic) {
        this.topic = topic;
    }

    @Autowired
    public void setKafkaTemplate(KafkaTemplate<String, OrderEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderEvent orderEvent) {

        log.info("Order event => {}", orderEvent);

        // create Message
        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
