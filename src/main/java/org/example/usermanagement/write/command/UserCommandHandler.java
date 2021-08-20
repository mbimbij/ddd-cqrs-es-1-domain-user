package org.example.usermanagement.write.command;

import lombok.RequiredArgsConstructor;
import org.example.usermanagement.write.domain.DomainEventPublisher;
import org.example.usermanagement.write.domain.UserAggregate;
import org.example.usermanagement.write.domain.UserCreatedEvent;
import org.example.usermanagement.write.domain.UserFactory;

@RequiredArgsConstructor
public class UserCommandHandler {
    private final UserFactory factory;
    private final DomainEventPublisher eventPublisher;

    public void createUser(CreateUserCommand createUserCommand) {
        UserAggregate userAggregate = factory.createUser(createUserCommand.getFirstName(),
                createUserCommand.getLastName(),
                createUserCommand.getEmailAddress());
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(userAggregate.getFirstName(),
                userAggregate.getLastName(),
                userAggregate.getEmailAddress());
        eventPublisher.publish(userCreatedEvent);
    }
}
