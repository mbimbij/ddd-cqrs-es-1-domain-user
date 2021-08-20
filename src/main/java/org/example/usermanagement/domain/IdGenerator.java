package org.example.usermanagement.domain;

@FunctionalInterface
public interface IdGenerator {
    UserId generateUserId();
}
