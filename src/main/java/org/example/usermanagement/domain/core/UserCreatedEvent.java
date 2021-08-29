package org.example.usermanagement.domain.core;

import lombok.Value;

@Value
public class UserCreatedEvent extends DomainEvent {
    UserName username;
    EmailAddress emailAddress;

    public UserCreatedEvent(UserId userId, UserName username, EmailAddress emailAddress) {
        this.userId = userId;
        this.username = username;
        this.emailAddress = emailAddress;
    }
}
