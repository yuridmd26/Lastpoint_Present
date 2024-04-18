package br.com.present.attendances.queues.subscriber;

import br.com.present.present.messenger.subscriber.impl.RabbitMqSubscriberImpl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Component
public class QueueAttendancesSubscriber extends RabbitMqSubscriberImpl {

    Logger logger = Logger.getLogger(getClass().getName());

    @Value("${mq.queues.names.attendance}")
    private String[] queueNames;

    @Override
    public void listen(String payload) {
        logger.log(Level.WARNING,"Queue: {0}", payload);
    }
}
