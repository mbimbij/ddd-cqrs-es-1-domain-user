package org.example.usermanagement.write.command;

import lombok.RequiredArgsConstructor;
import org.example.usermanagement.write.domain.*;
import org.example.usermanagement.utils.Pair;

@RequiredArgsConstructor
public class UserCommandHandler {
    private final UserFactory factory;
    private final DomainEventPublisher eventPublisher;

    public void createUser(CreateUserCommand createUserCommand) {
        Pair<UserAggregate, UserCreatedEvent> createdEventPair = factory.createUser(createUserCommand.getFirstName(), createUserCommand.getLastName(), createUserCommand.getEmailAddress());
        UserAggregate userAggregate = createdEventPair.getFirstElement();
        UserCreatedEvent userCreatedEvent = createdEventPair.getSecondElement();
        eventPublisher.publish(userCreatedEvent);
    }
}
