package org.example.usermanagement.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

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
        Optional<UserDeletedEvent> event = user.delete();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(event).hasValue(expectedEvent);
            softAssertions.assertThat(user.isDeleted()).isTrue();
        });
    }

    @Test
    void notReturnUserDeletedEvent_whenCallDeleteTwice() {
        // GIVEN
        UserId userId = nextUserId();
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        assertThat(user.isDeleted()).isFalse();
        user.delete();

        // WHEN
        Optional<UserDeletedEvent> event = user.delete();

        assertSoftly(softAssertions -> {
            softAssertions.assertThat(event).isEmpty();
            softAssertions.assertThat(user.isDeleted()).isTrue();
        });
    }

    @Test
    void beDeleted_whenHandleUserDeletedEvent() {
        // GIVEN
        UserId userId = nextUserId();
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        assertThat(user.isDeleted()).isFalse();

        // WHEN
        user.apply(new UserDeletedEvent(userId));

        // THEN
        assertThat(user.isDeleted()).isTrue();
    }
}
