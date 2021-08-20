package org.example.usermanagement;

import org.assertj.core.api.Assertions;
import org.example.usermanagement.domain.*;
import org.example.usermanagement.infra.inmemory.InMemoryUserRepository;
import org.example.usermanagement.service.UserApplicationService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

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

}
