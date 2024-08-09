package com.stock.service.manager.orderservice.publisher;

import com.stock.service.manager.orderservice.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceProducer.class);

    @Value("${rabbitmq.exchange.order.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.binding.email.routing.key}")
    private String emailRoutingKey;

    private RabbitTemplate template;

    public OrderServiceProducer(RabbitTemplate template) {
        this.template = template;
    }

    public void sendMessage(OrderEvent event){
        LOGGER.info(String.format("Order sent to Rabbitmq -> %s",event.toString()));
        //to send the order to order queue
        template.convertAndSend(exchange,routingKey,event);

        // to send the order to email queue
        template.convertAndSend(exchange,emailRoutingKey,event);
    }
}
