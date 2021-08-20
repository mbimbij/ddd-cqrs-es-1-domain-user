package org.example.usermanagement.application;

import org.example.usermanagement.domain.DomainEventPublisher;
import org.example.usermanagement.domain.UserFactory;
import org.example.usermanagement.domain.UserRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

public class UserApplicationServiceTest {
    @Test
    void canCreateUserApplicationService() {
        UserFactory factory = mock(UserFactory.class);
        UserRepository repository = mock(UserRepository.class);
        DomainEventPublisher eventPublisher = mock(DomainEventPublisher.class);
        UserApplicationService userApplicationService = new UserApplicationService(factory, repository, eventPublisher);
    }
}
