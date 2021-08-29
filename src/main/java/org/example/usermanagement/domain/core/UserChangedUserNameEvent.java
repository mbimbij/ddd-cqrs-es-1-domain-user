package org.example.usermanagement.domain.core;

public class UserChangedUserNameEvent extends DomainEvent {
    private final UserName newUserName;

    public UserChangedUserNameEvent(UserId userId, UserName newUserName) {
        this.userId = userId;
        this.newUserName = newUserName;
    }
}
