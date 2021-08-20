package org.example.usermanagement.service;

import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.*;

import java.util.Optional;

@RequiredArgsConstructor
public class UserApplicationService {
    private final UserFactory userFactory;
    private final UserRepository userRepository;

    public User createUser(FirstName firstName, LastName lastName, EmailAddress emailAddress) {
        User newUser = userFactory.createUser(firstName, lastName, emailAddress);
        return userRepository.save(newUser);
    }

    public Optional<User> getUserById(UserId userId) {
        return userRepository.getById(userId);
    }

    public User updateUser(UserId userId, FirstName newFirstName, LastName newLastName, EmailAddress newEmailAddress) {
        return userRepository.getById(userId)
                .map(user -> new User(userId, newFirstName, newLastName, newEmailAddress))
                .map(userRepository::save)
                .orElseThrow(() -> new IllegalArgumentException("user " + userId.toString() + " does not exist"));
    }
}
