package org.example.usermanagement.domain;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserFactory {
    private final IGenerateIds idGenerator;

    public User createUser(FirstName firstName, LastName lastName, EmailAddress emailAddress) {
        return new User(idGenerator.generateUserId(), firstName, lastName, emailAddress);
    }
}
