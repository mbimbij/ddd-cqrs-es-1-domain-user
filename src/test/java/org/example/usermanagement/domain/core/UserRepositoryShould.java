package org.example.usermanagement.domain.core;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.example.usermanagement.rightside.InMemoryEventStore;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.example.usermanagement.domain.core.UserId.nextUserId;

class UserRepositoryShould {
    @Test
    void returnEmpty_ifUserIdNotFound() {
        // GIVEN
        EventStore eventStore = new InMemoryEventStore(Collections.emptyList());
        UserRepository userRepository = new UserRepository(eventStore);
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
        EventStore eventStore = new InMemoryEventStore(pastEvents);
        UserRepository userRepository = new UserRepository(eventStore);
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
        EventStore eventStore = new InMemoryEventStore(pastEvents);
        UserRepository userRepository = new UserRepository(eventStore);
        User expectedUser = new User(userId, newUsername, emailAddress);

        // WHEN
        Optional<User> userOptional = userRepository.findById(userId);

        // THEN
        Assertions.assertThat(userOptional).hasValue(expectedUser);
    }

    @Test
    void returnAUser_givenAUserCreatedEventEmitted_andUserChangedUserNameEventEmitted_andUserChangedEmailAddressEventEmitted() {
        // GIVEN
        UserId userId = nextUserId();
        UserName username = new UserName("username");
        UserName newUsername = new UserName("newusername");
        EmailAddress emailAddress = new EmailAddress("emailAddress");
        EmailAddress newEmailAddress = new EmailAddress("newemailAddress");
        List<DomainEvent> pastEvents = List.of(
                new UserCreatedEvent(userId, username, emailAddress),
                new UserChangedUserNameEvent(userId, newUsername),
                new UserChangedEmailAddressEvent(userId, newEmailAddress)
        );
        EventStore eventStore = new InMemoryEventStore(pastEvents);
        UserRepository userRepository = new UserRepository(eventStore);
        User expectedUser = new User(userId, newUsername, newEmailAddress);

        // WHEN
        Optional<User> userOptional = userRepository.findById(userId);

        // THEN
        Assertions.assertThat(userOptional).hasValue(expectedUser);
    }

    @Test
    void distinguishBetween2Users_given2PastEventsHistories() {
        // GIVEN
        UserId userId1 = nextUserId();
        UserId userId2 = nextUserId();
        UserName username1 = new UserName("username1");
        UserName newUsername = new UserName("newusername");
        UserName username2 = new UserName("username1");
        EmailAddress emailAddress1 = new EmailAddress("emailAddress1");
        EmailAddress emailAddress2 = new EmailAddress("emailAddress2");
        List<DomainEvent> pastEvents = List.of(
                new UserCreatedEvent(userId1, username1, emailAddress1),
                new UserCreatedEvent(userId2, username2, emailAddress2),
                new UserChangedUserNameEvent(userId1, newUsername)
        );
        EventStore eventStore = new InMemoryEventStore(pastEvents);
        UserRepository userRepository = new UserRepository(eventStore);
        User expectedUser1 = new User(userId1, newUsername, emailAddress1);
        User expectedUser2 = new User(userId2, username2, emailAddress2);

        // WHEN
        Optional<User> userOptional1 = userRepository.findById(userId1);
        Optional<User> userOptional2 = userRepository.findById(userId2);

        // THEN
        SoftAssertions.assertSoftly(softAssertions -> {
            Assertions.assertThat(userOptional1).hasValue(expectedUser1);
            Assertions.assertThat(userOptional2).hasValue(expectedUser2);
        });
    }
}