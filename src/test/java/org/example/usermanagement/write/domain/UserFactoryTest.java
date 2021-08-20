package org.example.usermanagement.write.domain;

import org.example.usermanagement.utils.Pair;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class UserFactoryTest {

    @Test
    void createUser() {
        // GIVEN
        UserId userId = new UserId("id");
        UserFactory userFactory = new UserFactory(() -> userId);
        FirstName firstname = new FirstName("firstname");
        LastName lastname = new LastName("lastname");
        EmailAddress emailaddress = new EmailAddress("emailaddress");
        UserAggregate expectedUser = new UserAggregate(userId, firstname, lastname, emailaddress);
        UserCreatedEvent expectedEvent = new UserCreatedEvent(firstname, lastname, emailaddress);

        // WHEN
        Pair<UserAggregate, UserCreatedEvent> pair = userFactory.createUser(firstname, lastname, emailaddress);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(pair.getFirstElement()).usingRecursiveComparison().isEqualTo(expectedUser);
            softAssertions.assertThat(pair.getSecondElement()).usingRecursiveComparison().isEqualTo(expectedEvent);
        });
    }
}