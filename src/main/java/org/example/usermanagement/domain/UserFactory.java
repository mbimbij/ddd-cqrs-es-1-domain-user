package org.example.usermanagement.domain;

import org.example.usermanagement.utils.Pair;

import static org.example.usermanagement.domain.UserId.nextUserId;

public class UserFactory {
    public Pair<User, UserCreatedEvent> createUser(UserName username, EmailAddress emailAddress) {
        UserId newUserId = getNextUserId();
        User newUser = new User(newUserId, username, emailAddress);
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(newUserId, username, emailAddress);
        return new Pair<>(newUser, userCreatedEvent);
    }

    public UserId getNextUserId() {
        return nextUserId();
    }
}
