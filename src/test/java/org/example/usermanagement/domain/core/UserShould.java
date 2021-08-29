package org.example.usermanagement.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserShould {

    @Test
    void beCreated() {
        User user = new User(new UserId(1));
        assertThat(user.getUserId()).isEqualTo(new UserId(1));
    }

    @Test
    void returnUserNameChangedEvent_whenChangeUserName() {
        // GIVEN
        UserId userId = new UserId(1);
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
        UserId userId = new UserId(1);
        User user = new User(userId, new UserName("username"), new EmailAddress("toto@mail.com"));
        EmailAddress newEmailAddress = new EmailAddress("newEmailAddress");
        EmailAddressChangedEvent expectedEvent = new EmailAddressChangedEvent(userId, newEmailAddress);

        // WHEN
        EmailAddressChangedEvent event = user.changeEmailAddress(newEmailAddress);

        // THEN
        assertThat(event).isEqualTo(expectedEvent);
    }
}
