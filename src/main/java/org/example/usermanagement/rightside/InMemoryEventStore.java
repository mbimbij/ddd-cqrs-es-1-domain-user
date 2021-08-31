package org.example.usermanagement.rightside;

import org.example.usermanagement.domain.DomainEvent;
import org.example.usermanagement.domain.EventStore;
import org.example.usermanagement.domain.UserId;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.stream.Collectors;

public class InMemoryEventStore implements EventStore {
    private final Map<UserId, List<DomainEvent>> pastEvents;

    public InMemoryEventStore() {
        pastEvents = new HashMap<>();
    }

    public InMemoryEventStore(Collection<? extends DomainEvent> pastEvents) {
        this.pastEvents = pastEvents.stream()
                .collect(Collectors.groupingBy(DomainEvent::getUserId));
    }

    @Override
    public Collection<DomainEvent> getEventsByUserId(UserId userId) {
        return pastEvents.getOrDefault(userId, Collections.emptyList());
    }

    @Override
    public void store(DomainEvent event) {
        UserId userId = event.getUserId();
        pastEvents.putIfAbsent(userId, new ArrayList<>());
        pastEvents.get(userId).add(event);
    }

    @Override
    public void deleteAll() {
        pastEvents.clear();
    }

    @Override
    public Flux<DomainEvent> getAllEvents() {
        return Flux.fromIterable(getEventsAsCollection());
    }

    public Collection<DomainEvent> getEventsAsCollection(){
        return pastEvents.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
