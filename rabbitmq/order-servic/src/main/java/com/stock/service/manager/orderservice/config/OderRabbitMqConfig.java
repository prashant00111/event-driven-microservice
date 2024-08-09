package com.stock.service.manager.orderservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OderRabbitMqConfig {

    @Value("${rabbitmq.queue.order.name}")
    private String queue;

    @Value("${rabbitmq.queue.email.name}")
    private String emailQueue;

    @Value("${rabbitmq.exchange.order.name}")
    private String exchange;

    @Value("${rabbitmq.binding.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.binding.email.routing.key}")
    private String emailRoutingKey;

    //spring bean for queue - order queue
    @Bean
    public Queue orderQueue(){

        return new Queue(queue);
    }

    @Bean
    public Queue emailQueue(){

        return new Queue(emailQueue);
    }

    // spring bean for exchange
    @Bean
    public TopicExchange orderExchange(){

        return new TopicExchange(exchange);
    }

    //spring bean for binding b/w exchange and queue using routing key
    @Bean
    public Binding binding(){
        return BindingBuilder
                .bind(orderQueue())
                .to(orderExchange()).with(routingKey);
    }

    @Bean
    public Binding emailBinding(){
        return BindingBuilder
                .bind(emailQueue())
                .to(orderExchange()).with(emailRoutingKey);
    }

    //message converter
    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    // configure RabbitTemplate
    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
