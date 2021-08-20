package org.example.usermanagement.infra.inmemory;

import org.example.usermanagement.domain.User;
import org.example.usermanagement.domain.UserId;
import org.example.usermanagement.domain.UserRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository {
    private final Map<UserId, User> inMemoryDatabase = new HashMap<>();

    @Override
    public User save(User newUser) {
        UserId userId = newUser.getId();
        inMemoryDatabase.put(userId, newUser);
        return inMemoryDatabase.get(userId);
    }

    @Override
    public Optional<User> getById(UserId userId) {
        return Optional.ofNullable(inMemoryDatabase.get(userId));
    }
}
