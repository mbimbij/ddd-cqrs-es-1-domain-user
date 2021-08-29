package org.example.usermanagement.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.example.usermanagement.domain.core.UserId.nextUserId;

class UserRepositoryShould {
    @Test
    void returnEmpty_ifUserIdNotFound() {
        // GIVEN
        UserRepository userRepository = new UserRepository(Collections.emptyList());
        UserId userId = nextUserId();

        // WHEN
        Optional<User> userOptional = userRepository.findById(userId);

        // THEN
        Assertions.assertThat(userOptional).isEmpty();
    }

    @Test
    void returnAUser_givenAUserCreatedEventEmitted() {
        // GIVEN
        UserId userId = nextUserId();
        UserName username = new UserName("username");
        EmailAddress emailAddress = new EmailAddress("emailAddress");
        List<UserCreatedEvent> pastEvents = List.of(new UserCreatedEvent(userId, username, emailAddress));
        UserRepository userRepository = new UserRepository(pastEvents);
        User expectedUser = new User(userId, username, emailAddress);

        // WHEN
        Optional<User> userOptional = userRepository.findById(userId);

        // THEN
        Assertions.assertThat(userOptional).hasValue(expectedUser);
    }

    @Test
    void returnAUser_givenAUserCreatedEventEmitted_andUserChangedUserNameEventEmitted() {
        // GIVEN
        UserId userId = nextUserId();
        UserName username = new UserName("username");
        UserName newUsername = new UserName("newusername");
        EmailAddress emailAddress = new EmailAddress("emailAddress");
        List<DomainEvent> pastEvents = List.of(
                new UserCreatedEvent(userId, username, emailAddress),
                new UserChangedUserNameEvent(userId, newUsername)
        );
        UserRepository userRepository = new UserRepository(pastEvents);
        User expectedUser = new User(userId, newUsername, emailAddress);

        // WHEN
        Optional<User> userOptional = userRepository.findById(userId);

        // THEN
        Assertions.assertThat(userOptional).hasValue(expectedUser);
    }
}