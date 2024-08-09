package com.messaging.service.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);


    @KafkaListener(topics = "recent_msg_manager",groupId = "msgGroup")
    public void consume(String eventMessage){
        LOGGER.info(String.format("Event Message received ->  %s",eventMessage));

    }
}
