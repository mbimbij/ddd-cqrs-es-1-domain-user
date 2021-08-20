package org.example.usermanagement;

import org.assertj.core.api.SoftAssertions;
import org.example.usermanagement.domain.*;
import org.example.usermanagement.infra.inmemory.InMemoryUserRepository;
import org.example.usermanagement.service.UserApplicationService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class UserApplicationServiceTest {

    @Test
    void canCreateUser() {
        // GIVEN
        UserId userId = new UserId("someId");
        FirstName firstName = new FirstName("firstname");
        LastName lastName = new LastName("lastname");
        EmailAddress emailAddress = new EmailAddress("emailaddress");

        User expectedUser = new User(userId, firstName, lastName, emailAddress);

        UserFactory userFactory = new UserFactory(() -> userId);
        UserRepository userRepository = new InMemoryUserRepository();
        UserApplicationService userApplicationService = new UserApplicationService(userFactory, userRepository);

        // WHEN
        User user = userApplicationService.createUser(firstName, lastName, emailAddress);

        // THEN
        assertThat(user).usingRecursiveComparison().isEqualTo(expectedUser);
    }

    @Test
    void canCreateUserAndGetItBackFromRepository() {
        // GIVEN
        UserId userId = new UserId("someId");
        FirstName firstName = new FirstName("firstname");
        LastName lastName = new LastName("lastname");
        EmailAddress emailAddress = new EmailAddress("emailaddress");

        User expectedUser = new User(userId, firstName, lastName, emailAddress);

        UserFactory userFactory = new UserFactory(() -> userId);
        UserRepository userRepository = new InMemoryUserRepository();
        UserApplicationService userApplicationService = new UserApplicationService(userFactory, userRepository);

        // WHEN - THEN
        assertThat(userRepository.getById(userId)).isEmpty();

        // WHEN
        userApplicationService.createUser(firstName, lastName, emailAddress);

        // THEN
        assertThat(userRepository.getById(userId)).hasValue(expectedUser);

        // WHEN - THEN
        assertThat(userApplicationService.getUserById(userId)).hasValue(expectedUser);
    }


    @Test
    void canModifyUser() {
        // GIVEN
        UserId userId = new UserId("someId");
        FirstName firstName = new FirstName("firstname");
        LastName lastName = new LastName("lastname");
        EmailAddress emailAddress = new EmailAddress("emailaddress");

        FirstName firstName2 = new FirstName("firstname2");
        LastName lastName2 = new LastName("lastname2");
        EmailAddress emailAddress2 = new EmailAddress("emailaddress2");

        User expectedUser = new User(userId, firstName2, lastName2, emailAddress2);

        UserFactory userFactory = new UserFactory(() -> userId);
        UserRepository userRepository = new InMemoryUserRepository();
        UserApplicationService userApplicationService = new UserApplicationService(userFactory, userRepository);

        User newUser = userApplicationService.createUser(firstName, lastName, emailAddress);

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(newUser).isEqualTo(newUser);
            softAssertions.assertThat(userRepository.getById(userId)).hasValue(newUser);
        });

        // WHEN
        User updatedUser = userApplicationService.updateUser(userId, firstName2, lastName2, emailAddress2);
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(updatedUser).isEqualTo(expectedUser);
            softAssertions.assertThat(userRepository.getById(userId)).hasValue(expectedUser);
        });

    }
}
