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
        UserChangedUserNameEvent expectedEvent = new UserChangedUserNameEvent(userId, newUserName);

        // WHEN
        UserChangedUserNameEvent event = user.changeUsername(newUserName);

        // THEN
        assertThat(event).isEqualTo(expectedEvent);
    }
}
