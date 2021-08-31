package org.example.usermanagement.domain;


import java.util.Collection;

public interface EventStore {
    Collection<DomainEvent> getEventsByUserId(UserId userId);
    void store(DomainEvent event);
    void deleteAll();
    Collection<DomainEvent> getAllEvents();
}
