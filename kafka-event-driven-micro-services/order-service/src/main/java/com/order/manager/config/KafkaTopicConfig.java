package com.order.manager.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.topic.name}")
    private String topic;

    // Spring bean for kafka topic

    @Bean
    public NewTopic createTopic(){

        return TopicBuilder.name(topic).build();
    }
}

