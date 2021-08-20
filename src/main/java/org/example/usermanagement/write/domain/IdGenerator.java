package org.example.usermanagement.write.domain;

@FunctionalInterface
public interface IdGenerator {
    UserId generateUserId();
}
