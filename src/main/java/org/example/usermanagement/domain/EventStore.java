package org.example.usermanagement.domain;

import reactor.core.publisher.Flux;

import java.util.Collection;

public interface EventStore {
    Collection<DomainEvent> getEventsByUserId(UserId userId);
    void store(DomainEvent event);
    void deleteAll();
    Flux<DomainEvent> getAllEvents();
}
