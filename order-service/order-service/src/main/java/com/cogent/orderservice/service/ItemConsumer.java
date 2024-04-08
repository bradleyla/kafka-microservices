package com.cogent.orderservice.service;

import com.cogent.orderservice.payload.ItemEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ItemConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemConsumer.class);

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consume(ItemEvent itemEvent) {
        LOGGER.info(String.format("Item event received in order service -> %s", itemEvent.toString()));
    }
}
