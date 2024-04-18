package br.com.present.present.messenger.config;

import br.com.present.present.messenger.exception.ApiQueueNotExist;
import br.com.present.present.messenger.service.impl.RabbitMqBaseServiceImpl;
import br.com.present.present.messenger.subscriber.impl.RabbitMqSubscriberImpl;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerEndpoint;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class RabbitMqConfig implements RabbitListenerConfigurer {

    private ApplicationContext applicationContext;
    private RabbitMqBaseServiceImpl mqService;

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar registrar) {
        Map<String, RabbitMqSubscriberImpl> subscribers = applicationContext.getBeansOfType(RabbitMqSubscriberImpl.class);
        subscribers.forEach((name, subscriber) -> {
            List<String> lQueueFilterExists = queueFilterExists(subscriber);

            if(!lQueueFilterExists.isEmpty()){
                SimpleRabbitListenerEndpoint endpoint = new SimpleRabbitListenerEndpoint();
                endpoint.setId(name);
                endpoint.setQueueNames(lQueueFilterExists.toArray(new String[]{}));
                endpoint.setMessageListener(m -> subscriber.listen(new String(m.getBody())));
                registrar.registerEndpoint(endpoint);
            }
        });
    }

    private List<String> queueFilterExists(RabbitMqSubscriberImpl subscriber) {
        List<String> lQueue = List.of(subscriber.getQueueNames());
        return lQueue.stream().filter(q -> {
            try{
                return mqService.checkExistenceQueue(q);
            }catch (ApiQueueNotExist e){
                return false;
            }
        }).toList();
    }
}