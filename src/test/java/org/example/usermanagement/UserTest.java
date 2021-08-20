package org.example.usermanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class UserTest {

    private UserId id;
    private User user;

    @BeforeEach
    void setUp() {
        id = new UserId("id");
        user = new User(id);
    }

    @Test
    void canApply1EventToUser() {
        // GIVEN
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
            softAssertions.assertThat(user.isDeleted()).isFalse();
        });
    }

    @Test
    void canApplyMultipleEventsToUser() {
        // GIVEN
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
                new FirstName("firstname"),
                new LastName("lastname"),
                new EmailAddress("emailaddress")
        );
        UserChangedFirstNameEvent userChangedFirstNameEvent = new UserChangedFirstNameEvent(new FirstName("firstname2"));
        UserChangedFirstNameEvent userChangedFirstNameEvent2 = new UserChangedFirstNameEvent(new FirstName("firstname3"));
        UserChangedLastNameEvent userChangedLastNameEvent = new UserChangedLastNameEvent(new LastName("lastname2"));
        UserChangedEmailAddressEvent userChangedEmailAddressEvent = new UserChangedEmailAddressEvent(new EmailAddress("emailaddress2"));
        UserDeletedEvent userDeletedEvent = new UserDeletedEvent();

        // WHEN
        user.apply(userCreatedEvent);
        user.apply(userChangedFirstNameEvent);
        user.apply(userChangedFirstNameEvent2);
        user.apply(userChangedLastNameEvent);
        user.apply(userChangedEmailAddressEvent);
        user.apply(userDeletedEvent);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(user.getFirstName()).isEqualTo(new FirstName("firstname3"));
            softAssertions.assertThat(user.getLastName()).isEqualTo(new LastName("lastname2"));
            softAssertions.assertThat(user.getEmailAddress()).isEqualTo(new EmailAddress("emailaddress2"));
            softAssertions.assertThat(user.isDeleted()).isTrue();
        });
    }
}
