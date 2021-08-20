package org.example.usermanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.*;

@RequiredArgsConstructor
public class UserApplicationService {
    private final UserFactory userFactory;
    private final UserRepository userRepository;

    public User createUser(FirstName firstName, LastName lastName, EmailAddress emailAddress) {
        User newUser = userFactory.createUser(firstName, lastName, emailAddress);
        return userRepository.save(newUser);
    }
}
