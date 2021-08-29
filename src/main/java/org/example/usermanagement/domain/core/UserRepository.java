package org.example.usermanagement.domain.core;

import java.util.*;
import java.util.stream.Collectors;

public class UserRepository {

    private final Map<UserId, List<DomainEvent>> pastEvents;

    public UserRepository(Collection<? extends DomainEvent> pastEvents) {
        this.pastEvents = pastEvents.stream()
                .collect(Collectors.groupingBy(DomainEvent::getUserId));
    }

    public Optional<User> findById(UserId userId) {
        List<DomainEvent> userPastEvents = pastEvents.getOrDefault(userId, Collections.emptyList());
        return Optional.of(userPastEvents)
                .filter(events -> !events.isEmpty())
                .map(domainEvents -> buildUserFromPastEvents(userId, userPastEvents));
    }

    private User buildUserFromPastEvents(UserId userId, List<DomainEvent> userPastEvents) {
        User newUser = new User(userId, null, null);
        for (DomainEvent event : userPastEvents) {
            newUser.apply(event);
        }
        return newUser;
    }
}
