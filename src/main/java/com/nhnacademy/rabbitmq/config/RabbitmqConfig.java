package com.nhnacademy.rabbitmq.config;

import com.nhnacademy.rabbitmq.Listener.CustomerMessageListner;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Some description here.
 *
 * @author : 정유진
 * @since : 1.0
 **/
@Configuration
@RequiredArgsConstructor
//@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitmqConfig {
    private final CustomerMessageListner customerMessageListner;
//    @Value("${spring.rabbitmq.host}")
//    private String host;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Value("${spring.rabbitmq.url}")
    private String url;

//    @Value("${spring.rabbitmq.port}")
//    private int port;

    @Value("${spring.rabbitmq.virtual-host}")
    private String vhost;

//    public void MessageQueueConfig(){}

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new
                CachingConnectionFactory();
//        connectionFactory.setHost(host);
        connectionFactory.setVirtualHost(vhost);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
//        connectionFactory.setPort(port);
        connectionFactory.setUri(url);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(new
                Jackson2JsonMessageConverter());
        rabbitTemplate.setRoutingKey("message.queue");
        return rabbitTemplate;
    }


    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public Queue mailQueue() {
        return new Queue("message.queue");
    }

    @Bean
    public MessageListenerAdapter mailListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter();
        adapter.setDelegate(customerMessageListner);
        adapter.setDefaultListenerMethod("displayMessage");
        adapter.setMessageConverter(new Jackson2JsonMessageConverter());
        return adapter;

    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new
                SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory());
        container.setMessageListener(mailListenerAdapter());
        container.setQueues(mailQueue());
        return container;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //    public Integer getPort() {
//        return port;
//    }
//
//    public void setPort(Integer port) {
//        this.port = port;
//    }
//
//    public String getHost() {
//        return host;
//    }
//
//    public void setHost(String host) {
//        this.host = host;
//    }
//
    public String getVhost() {
        return vhost;
    }

    public void setVhost(String vhost) {
        this.vhost = vhost;
    }
}

