package org.example.usermanagement.domain.core;

import org.assertj.core.api.SoftAssertions;
import org.example.usermanagement.utils.Pair;
import org.junit.jupiter.api.Test;

class UserFactoryShould {

    @Test
    void createAUserAndEvent_whenCreateUser() {
        // GIVEN
        UserFactory userFactory = new UserFactory();
        UserName username = new UserName("username");
        EmailAddress emailAddress = new EmailAddress("emailAddress");

        User expectedUser = new User(null, username, emailAddress);
        UserCreatedEvent expectedEvent = new UserCreatedEvent(null, username, emailAddress);

        // WHEN
        Pair<User, UserCreatedEvent> userEventPair = userFactory.createUser(username, emailAddress);

        // THEN
        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(userEventPair.getValue1())
                    .usingRecursiveComparison()
                    .ignoringFields("id")
                    .isEqualTo(expectedUser);
            softAssertions.assertThat(userEventPair.getValue2())
                    .usingRecursiveComparison()
                    .ignoringFields("userId")
                    .isEqualTo(expectedEvent);
        });
    }
}