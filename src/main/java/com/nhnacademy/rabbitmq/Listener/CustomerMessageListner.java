package com.nhnacademy.rabbitmq.Listener;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Service
public class CustomerMessageListner {
    @RabbitListener(queues = "spring-boot")
    public void receiveMessage(final Message message) {
        System.out.println(message);
    }
}
