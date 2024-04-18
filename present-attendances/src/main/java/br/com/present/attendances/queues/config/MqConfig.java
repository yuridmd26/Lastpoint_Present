package br.com.present.attendances.queues.config;

import br.com.present.attendances.queues.service.MqService;
import br.com.present.present.messenger.config.RabbitMqConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MqConfig extends RabbitMqConfig {

    public MqConfig(ApplicationContext applicationContext, MqService mqService) {
        super(applicationContext, mqService);
    }
}