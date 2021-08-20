package org.example.usermanagement.infra;

import org.example.usermanagement.domain.*;
import org.example.utils.Pair;

public class UserFactoryImpl extends UserFactory {
    public UserFactoryImpl(IdGenerator idGenerator) {
        super(idGenerator);
    }

    @Override
    public Pair<UserAggregateRoot, UserCreatedEvent> createUser(FirstName firstName, LastName lastName, EmailAddress emailAddress) {
        UserId userId = getIdGenerator().generateUserId();
        UserAggregateRoot userAggregateRoot = new UserAggregateRoot(userId, firstName, lastName, emailAddress);
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(firstName, lastName, emailAddress);
        return new Pair<>(userAggregateRoot, userCreatedEvent);
    }
}
