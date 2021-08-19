package org.example.usermanagement;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class UserTest {

    private UserId id;

    @BeforeEach
    void setUp() {
        id = new UserId("id");
    }

    @Test
    void canCreateUser() {
        UserId userId = id;
        User user = new User(userId);
        assertThat(user.getId()).isEqualTo(new UserId("id"));
    }

    @Test
    void canApply1EventToUser() {
        // GIVEN
        User user = new User(id);
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
                new FirstName("firstname"),
                new LastName("lastname"),
                new EmailAddress("emailaddress")
        );

        // WHEN
        user.apply(userCreatedEvent);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(user.getFirstName()).isEqualTo(new FirstName("firstname"));
            softAssertions.assertThat(user.getLastName()).isEqualTo(new LastName("lastname"));
            softAssertions.assertThat(user.getEmailAddress()).isEqualTo(new EmailAddress("emailaddress"));
        });
    }
}
