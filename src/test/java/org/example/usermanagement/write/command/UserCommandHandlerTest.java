package org.example.usermanagement.write.command;

import org.example.usermanagement.write.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class UserCommandHandlerTest {

    private UserCommandHandler userCommandHandler;

    @BeforeEach
    void setUp() {
        UserId userId = new UserId("id");
        UserFactory factory = new UserFactory(() -> userId);
        DomainEventPublisher eventPublisher = mock(DomainEventPublisher.class);
        userCommandHandler = new UserCommandHandler(factory, eventPublisher);
    }

    @Test
    void canExecuteCreateUserCommand() {
        CreateUserCommand createUserCommand = new CreateUserCommand(new FirstName("firstname"),
                new LastName("lastname"),
                new EmailAddress("emailaddress"));
        userCommandHandler.createUser(createUserCommand);
    }
}
