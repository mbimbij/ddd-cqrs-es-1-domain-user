package org.example.usermanagement.write.domain;

import java.util.ArrayList;
import java.util.List;

public class DomainEventPublisher {

    private final List<ISubscribeToEvents> subscribers = new ArrayList<>();

    void subscribe(ISubscribeToEvents subscriber) {
        subscribers.add(subscriber);
    }

    void unsubscribe(ISubscribeToEvents subscriber) {
        subscribers.remove(subscriber);
    }

    public void publish(DomainEvent domainEvent) {
        subscribers.forEach(subscriber -> subscriber.handle(domainEvent));
    }
}
