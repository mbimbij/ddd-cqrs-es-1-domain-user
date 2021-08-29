package org.example.usermanagement.domain;

import lombok.Value;

@Value
public class UserChangedEmailAddressEvent extends DomainEvent {
    EmailAddress newEmailAddress;

    public UserChangedEmailAddressEvent(UserId userId, EmailAddress newEmailAddress) {
        this.userId = userId;
        this.newEmailAddress = newEmailAddress;
    }
}
