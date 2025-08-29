package com.miempresa.serviceorder.service;

import com.miempresa.serviceorder.dto.producer.OrderKafka;
import com.miempresa.serviceorder.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {
    @Autowired
    private KafkaTemplate<String, OrderKafka> kafkaTemplate;

    public void sendOrder(String topicName, OrderKafka order) {
        kafkaTemplate.send(topicName, order);
        System.out.println("Order send to Kafka: " + order);
    }
}
