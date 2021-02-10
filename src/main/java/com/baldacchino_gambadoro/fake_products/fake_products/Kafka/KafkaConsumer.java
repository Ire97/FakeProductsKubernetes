package com.baldacchino_gambadoro.fake_products.fake_products.Kafka;

import com.baldacchino_gambadoro.fake_products.fake_products.ClassSerializable.OrderCompleted;
import com.baldacchino_gambadoro.fake_products.fake_products.ClassSerializable.OrderValidation;
import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class KafkaConsumer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value(value = "${KAFKA_TOPIC_ORDERS}")
    private String kafkaTopicOrders;

    @Value(value="${STATUS}")
    private Integer status;

    @KafkaListener(topics = "${KAFKA_TOPIC_ORDERS}", groupId = "${KAFKA_GROUP_ID}")
    public void listenOrderCompleted(ConsumerRecord<String, String> record) {

        if(record != null && record.key().equals("order_completed")) {
            OrderCompleted orderCompleted = new Gson().fromJson(record.value(), OrderCompleted.class);
            String id = orderCompleted.getOrderId();
            OrderValidation orderValidation = new OrderValidation(LocalDateTime.now(), status, id);
            kafkaTemplate.send(kafkaTopicOrders, "order_validation", new Gson().toJson(orderValidation));
            System.out.println("Sto inviando sul topic order_validation ciò che ho ricevuto da order_completed");
        }

    }

}
