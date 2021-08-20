package org.example.usermanagement.write.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.utils.Pair;

@RequiredArgsConstructor
@Getter
public class UserFactory {
    private final IdGenerator idGenerator;

    public Pair<UserAggregate, UserCreatedEvent> createUser(FirstName firstName, LastName lastName, EmailAddress emailAddress) {
        UserId userId = getIdGenerator().generateUserId();
        UserAggregate userAggregate = new UserAggregate(userId, firstName, lastName, emailAddress);
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(firstName, lastName, emailAddress);
        return new Pair<>(userAggregate, userCreatedEvent);
    }
}
