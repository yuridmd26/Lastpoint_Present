package br.com.present.present.messenger.service.impl;

import br.com.present.present.messenger.exception.ApiQueueNotExist;
import br.com.present.present.messenger.exception.keys.MessengerExceptionKeys;
import br.com.present.present.messenger.service.IMqService;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public abstract class RabbitMqBaseServiceImpl implements IMqService {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private Integer port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Override
    public boolean checkExistenceQueue(String queue) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(username);
        factory.setPassword(password);

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclarePassive(queue);
            return true;
        } catch (IOException | TimeoutException e) {
            throw new ApiQueueNotExist(MessengerExceptionKeys.QUEUE_NOT_EXIST, new String[]{queue});
        }
    }

}
