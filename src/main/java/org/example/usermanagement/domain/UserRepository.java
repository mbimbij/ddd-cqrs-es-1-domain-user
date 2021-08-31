package org.example.usermanagement.domain;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class UserRepository {

    private final EventStore eventStore;

    public Optional<User> findById(UserId userId) {
        Collection<DomainEvent> userPastEvents = eventStore.getEventsByUserId(userId);
        return Optional.of(userPastEvents)
                .filter(events -> !events.isEmpty())
                .map(domainEvents -> buildUserFromPastEvents(userId, userPastEvents));
    }

    private User buildUserFromPastEvents(UserId userId, Collection<DomainEvent> userPastEvents) {
        User newUser = new User(userId, null, null);
        for (DomainEvent event : userPastEvents) {
            newUser.apply(event);
        }
        return newUser;
    }

    public Flux<User> findAll() {
        return eventStore.getAllEvents()
                .collect(Collectors.groupingBy(DomainEvent::getUserId))
                .flatMapIterable(pastEvents -> new ArrayList<>(pastEvents.entrySet()))
                .map(userPastEvents -> buildUserFromPastEvents(userPastEvents.getKey(), userPastEvents.getValue()));
    }
}
