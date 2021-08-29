package org.example.usermanagement.rightside;

import org.example.usermanagement.domain.core.DomainEvent;
import org.example.usermanagement.domain.EventStore;
import org.example.usermanagement.domain.UserId;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryEventStore implements EventStore {
    private final Map<UserId, List<DomainEvent>> pastEvents;

    public InMemoryEventStore(Collection<? extends DomainEvent> pastEvents) {
        this.pastEvents = pastEvents.stream()
                .collect(Collectors.groupingBy(DomainEvent::getUserId));
    }

    @Override
    public Collection<DomainEvent> getEventsByUserId(UserId userId) {
        return pastEvents.getOrDefault(userId, Collections.emptyList());
    }
}
