package com.stock.service.manager.emailservice.consumer;

import com.stock.service.manager.emailservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.email.name}"})
    public void consume(OrderEvent orderEvent){
        LOGGER.info(String.format("order received to RabbitMq email -. %s ",orderEvent.toString()));
    }
}
