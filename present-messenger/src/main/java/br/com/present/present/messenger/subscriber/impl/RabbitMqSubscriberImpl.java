package br.com.present.present.messenger.subscriber.impl;

import br.com.present.present.messenger.subscriber.ISubscriber;

public abstract class RabbitMqSubscriberImpl implements ISubscriber {

    public abstract String[] getQueueNames();

}
