package org.example.usermanagement.infra.inmemory;

import org.example.usermanagement.domain.User;
import org.example.usermanagement.domain.UserRepository;

public class InMemoryUserRepository implements UserRepository {
    @Override
    public User save(User newUser) {
        return newUser;
    }
}
