package org.example.usermanagement.infra;

import org.assertj.core.api.SoftAssertions;
import org.example.usermanagement.domain.*;
import org.example.utils.Pair;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

class UserFactoryImplTest {

    @Test
    void createUser() {
        // GIVEN
        UserId userId = new UserId("id");
        UserFactoryImpl userFactory = new UserFactoryImpl(() -> userId);
        FirstName firstname = new FirstName("firstname");
        LastName lastname = new LastName("lastname");
        EmailAddress emailaddress = new EmailAddress("emailaddress");
        UserAggregateRoot expectedUser = new UserAggregateRoot(userId, firstname, lastname, emailaddress);
        UserCreatedEvent expectedEvent = new UserCreatedEvent(firstname, lastname, emailaddress);

        // WHEN
        Pair<UserAggregateRoot, UserCreatedEvent> pair = userFactory.createUser(firstname, lastname, emailaddress);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(pair.getFirstElement()).usingRecursiveComparison().isEqualTo(expectedUser);
            softAssertions.assertThat(pair.getSecondElement()).usingRecursiveComparison().isEqualTo(expectedEvent);
        });
    }
}