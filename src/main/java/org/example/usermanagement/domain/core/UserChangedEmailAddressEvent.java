package org.example.usermanagement.domain.core;

public class UserChangedEmailAddressEvent extends DomainEvent {
    private final EmailAddress newEmailAddress;

    public UserChangedEmailAddressEvent(UserId userId, EmailAddress newEmailAddress) {
        this.userId = userId;
        this.newEmailAddress = newEmailAddress;
    }
}
