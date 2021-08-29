package org.example.usermanagement.domain.core;

import java.util.Collection;

public interface EventStore {
    Collection<DomainEvent> getEventsByUserId(UserId userId);
}
