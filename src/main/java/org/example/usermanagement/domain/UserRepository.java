package org.example.usermanagement.domain;

import java.util.Optional;

public interface UserRepository {
    User save(User newUser);

    Optional<User> getById(UserId userId);
}
