package org.example.usermanagement.domain.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserShould {

    @Test
    void beCreated() {
        User user = new User(new UserId(1));
        assertThat(user.getUserId()).isEqualTo(new UserId(1));
    }
}
