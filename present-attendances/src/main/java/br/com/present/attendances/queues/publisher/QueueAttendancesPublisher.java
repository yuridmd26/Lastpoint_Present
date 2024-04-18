package br.com.present.attendances.queues.publisher;

import br.com.present.present.messenger.publisher.impl.RabbitMqPublisherImpl;
import br.com.present.present.messenger.service.impl.RabbitMqBaseServiceImpl;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QueueAttendancesPublisher extends RabbitMqPublisherImpl {

    public QueueAttendancesPublisher(RabbitTemplate rabbitTemplate, RabbitMqBaseServiceImpl mqService) {
        super(rabbitTemplate, mqService);
    }

    @Value("${mq.queues.names.attendance}")
    protected String[] queueNames;

    @Override
    protected String getQueueName() {
        return queueNames.length > 0 ? queueNames[0] : "";
    }
}
