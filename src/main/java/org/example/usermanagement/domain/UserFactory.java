package org.example.usermanagement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.utils.Pair;

@RequiredArgsConstructor
@Getter
public abstract class UserFactory {
    private final IdGenerator idGenerator;

    public abstract Pair<UserAggregateRoot, UserCreatedEvent> createUser(FirstName firstName, LastName lastName, EmailAddress emailAddress);
}
