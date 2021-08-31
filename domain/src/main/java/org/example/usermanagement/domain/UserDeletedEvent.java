package org.example.usermanagement.domain;

import lombok.Value;

@Value
public class UserDeletedEvent extends DomainEvent {
    public UserDeletedEvent(UserId userId) {
        this.userId = userId;
    }
}
