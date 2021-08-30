package org.example.usermanagement.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.example.usermanagement.domain.UserId.nextUserId;

public class UserShould {

    @Test
    void beCreated() {
        UserId userId = nextUserId();
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        assertThat(user.getId()).isEqualTo(userId);
    }

    @Test
    void returnUserNameChangedEvent_whenChangeUserName() {
        // GIVEN
        UserId userId = nextUserId();
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        UserName newUserName = new UserName("othername");
        UserChangedUserNameEvent expectedEvent = new UserChangedUserNameEvent(userId, newUserName);

        // WHEN
        UserChangedUserNameEvent event = user.changeUsername(newUserName);

        // THEN
        assertThat(event).isEqualTo(expectedEvent);
    }

    @Test
    void returnEmailAddressChangedEvent_whenChangeEmailAddress() {
        // GIVEN
        UserId userId = nextUserId();
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        EmailAddress newEmailAddress = new EmailAddress("newEmailAddress");
        UserChangedEmailAddressEvent expectedEvent = new UserChangedEmailAddressEvent(userId, newEmailAddress);

        // WHEN
        UserChangedEmailAddressEvent event = user.changeEmailAddress(newEmailAddress);

        // THEN
        assertThat(event).isEqualTo(expectedEvent);
    }

    @Test
    void beDeleted_whenCallDelete() {
        // GIVEN
        UserId userId = nextUserId();
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        assertThat(user.isDeleted()).isFalse();

        UserDeletedEvent expectedEvent = new UserDeletedEvent(userId);

        // WHEN
        UserDeletedEvent event = user.delete();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(event).isEqualTo(expectedEvent);
            softAssertions.assertThat(user.isDeleted()).isTrue();
        });
    }
}
