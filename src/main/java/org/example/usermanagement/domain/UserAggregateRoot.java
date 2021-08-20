package org.example.usermanagement.domain;

import lombok.Getter;
import org.example.usermanagement.application.*;

@Getter
public class UserAggregateRoot {
    private final UserId id;
    private FirstName firstName;
    private LastName lastName;
    private EmailAddress emailAddress;
    private boolean deleted;

    public UserAggregateRoot(UserId id) {
        this.id = id;
    }

    public UserAggregateRoot(UserId userId, FirstName firstName, LastName lastName, EmailAddress emailAddress) {
        id = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
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

    public UserCreatedEvent handle(CreateUserCommand command) {
        firstName = command.getFirstName();
        lastName = command.getLastName();
        emailAddress = command.getEmailAddress();
        return new UserCreatedEvent(firstName, lastName, emailAddress);
    }

    public UserChangedFirstNameEvent handle(ChangeUserFirstNameCommand command) {
        firstName = command.getFirstName();
        return new UserChangedFirstNameEvent(firstName);
    }

    public UserChangedLastNameEvent handle(ChangeUserLastNameCommand command) {
        lastName = command.getLastName();
        return new UserChangedLastNameEvent(lastName);
    }

    public UserChangedEmailAddressEvent handle(ChangeUserEmailAddressCommand command) {
        emailAddress = command.getEmailAddress();
        return new UserChangedEmailAddressEvent(emailAddress);
    }

    public UserDeletedEvent handle(DeleteUserCommand command) {
        deleted = true;
        return new UserDeletedEvent();
    }
}
