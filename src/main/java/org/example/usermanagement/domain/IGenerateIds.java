package org.example.usermanagement.domain;

@FunctionalInterface
public interface IGenerateIds {
    UserId generateUserId();
}
