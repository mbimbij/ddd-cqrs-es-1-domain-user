package org.example.usermanagement.domain.core;

import org.example.usermanagement.utils.Pair;

import static org.example.usermanagement.domain.core.UserId.nextUserId;

public class UserFactory {
    public Pair<User, UserCreatedEvent> createUser(UserName username, EmailAddress emailAddress) {
        User newUser = new User(nextUserId(), username, emailAddress);
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(nextUserId(), username, emailAddress);
        return new Pair<>(newUser, userCreatedEvent);
    }
}
