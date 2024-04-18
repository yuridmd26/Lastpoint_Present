package br.com.present.present.messenger.publisher.impl;

import br.com.present.commons.util.PresentJsonUtils;
import br.com.present.present.messenger.publisher.IPublisher;
import br.com.present.present.messenger.service.impl.RabbitMqBaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@AllArgsConstructor
public abstract class RabbitMqPublisherImpl implements IPublisher {

    private final RabbitTemplate rabbitTemplate;
    private RabbitMqBaseServiceImpl mqService;

    protected abstract String getQueueName();

    @Override
    public void publish(Object payload) {
        if(!getQueueName().isBlank()){
            mqService.checkExistenceQueue(getQueueName());
            String json = PresentJsonUtils.toJson(payload);
            rabbitTemplate.convertAndSend(getQueueName(), json);
        }
    }
}
