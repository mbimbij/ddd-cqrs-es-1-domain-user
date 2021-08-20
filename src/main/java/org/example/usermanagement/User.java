package org.example.usermanagement;

import lombok.Getter;

@Getter
public class User {
    private final UserId id;
    private FirstName firstName;
    private LastName lastName;
    private EmailAddress emailAddress;
    private boolean deleted;

    User(UserId id) {
        this.id = id;
    }

    public void apply(UserCreatedEvent userCreatedEvent) {
        deleted = false;
        firstName = userCreatedEvent.getFirstName();
        lastName = userCreatedEvent.getLastName();
        emailAddress = userCreatedEvent.getEmailAddress();
    }

    public void apply(UserChangedFirstNameEvent userChangedFirstNameEvent) {
        firstName = userChangedFirstNameEvent.getFirstname();
    }

    public void apply(UserChangedLastNameEvent userChangedLastNameEvent) {
        lastName = userChangedLastNameEvent.getLastname();
    }

    public void apply(UserChangedEmailAddressEvent userChangedEmailAddressEvent) {
        emailAddress = userChangedEmailAddressEvent.getEmailAddress();
    }

    public void apply(UserDeletedEvent userDeletedEvent) {
        deleted = true;
    }

}
