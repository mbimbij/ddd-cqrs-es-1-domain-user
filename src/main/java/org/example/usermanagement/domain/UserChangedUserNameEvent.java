package org.example.usermanagement.domain;

import lombok.Value;

@Value
public class UserChangedUserNameEvent extends DomainEvent {
    UserName newUserName;

    public UserChangedUserNameEvent(UserId userId, UserName newUserName) {
        this.userId = userId;
        this.newUserName = newUserName;
    }
}
