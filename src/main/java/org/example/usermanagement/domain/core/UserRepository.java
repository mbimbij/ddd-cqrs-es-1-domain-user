package org.example.usermanagement.domain.core;

import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.Optional;

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
}
