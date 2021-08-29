package org.example.usermanagement.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.usermanagement.domain.core.UserId.nextUserId;

public class UserShould {

    @Test
    void beCreated() {
        UserId userId = nextUserId();
        User user = new User(userId);
        assertThat(user.getUserId()).isEqualTo(userId);
    }

    @Test
    void returnUserNameChangedEvent_whenChangeUserName() {
        // GIVEN
        UserId userId = nextUserId();
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        UserName newUserName = new UserName("othername");
        UserNameChangedEvent expectedEvent = new UserNameChangedEvent(userId, newUserName);

        // WHEN
        UserNameChangedEvent event = user.changeUsername(newUserName);

        // THEN
        assertThat(event).isEqualTo(expectedEvent);
    }

    @Test
    void returnEmailAddressChangedEvent_whenChangeEmailAddress() {
        // GIVEN
        UserId userId = nextUserId();
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        EmailAddress newEmailAddress = new EmailAddress("newEmailAddress");
        EmailAddressChangedEvent expectedEvent = new EmailAddressChangedEvent(userId, newEmailAddress);

        // WHEN
        EmailAddressChangedEvent event = user.changeEmailAddress(newEmailAddress);

        // THEN
        assertThat(event).isEqualTo(expectedEvent);
    }
}
