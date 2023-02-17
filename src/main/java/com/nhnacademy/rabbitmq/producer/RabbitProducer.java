package com.nhnacademy.rabbitmq.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
//@RequiredArgsConstructor
public class RabbitProducer implements CommandLineRunner {
    private static final String topicExchange = "spring-boot-exchange";

    private final RabbitTemplate rabbitTemplate;

    public RabbitProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void run(String... args) {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(topicExchange, "foo.bar.baz", "Hello Message!");
    }
}