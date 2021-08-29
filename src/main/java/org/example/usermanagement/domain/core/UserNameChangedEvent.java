package org.example.usermanagement.domain.core;

public class UserNameChangedEvent extends DomainEvent {
    private final UserName newUserName;

    public UserNameChangedEvent(UserId userId, UserName newUserName) {
        this.userId = userId;
        this.newUserName = newUserName;
    }
}
