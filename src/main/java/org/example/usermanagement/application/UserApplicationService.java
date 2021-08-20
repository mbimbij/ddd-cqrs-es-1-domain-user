package org.example.usermanagement.application;

import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.DomainEventPublisher;
import org.example.usermanagement.domain.UserFactory;
import org.example.usermanagement.domain.UserRepository;

@RequiredArgsConstructor
public class UserApplicationService {
    private final UserFactory factory;
    private final UserRepository repository;
    private final DomainEventPublisher eventPublisher;
}
