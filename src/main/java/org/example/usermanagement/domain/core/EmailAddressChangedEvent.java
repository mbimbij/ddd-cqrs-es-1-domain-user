package org.example.usermanagement.domain.core;

public class EmailAddressChangedEvent extends DomainEvent {
    private final EmailAddress newEmailAddress;

    public EmailAddressChangedEvent(UserId userId, EmailAddress newEmailAddress) {
        this.userId = userId;
        this.newEmailAddress = newEmailAddress;
    }
}
