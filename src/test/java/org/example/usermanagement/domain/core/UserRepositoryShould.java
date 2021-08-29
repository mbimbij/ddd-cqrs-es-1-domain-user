package org.example.usermanagement.domain.core;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.example.usermanagement.domain.core.UserId.nextUserId;

class UserRepositoryShould {
    @Test
    void returnEmpty_ifUserIdNotFound() {
        // GIVEN
        UserRepository userRepository = new UserRepository();
        UserId userId = nextUserId();

        // WHEN
        Optional<User> userOptional = userRepository.findById(userId);

        // THEN
        Assertions.assertThat(userOptional).isEmpty();
    }
}