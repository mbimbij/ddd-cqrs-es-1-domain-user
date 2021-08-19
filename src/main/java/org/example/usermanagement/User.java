package org.example.usermanagement;

import lombok.Getter;

@Getter
public class User {
    private final UserId id;
    private FirstName firstName;
    private LastName lastName;
    private EmailAddress emailAddress;

    User(UserId id) {
        this.id = id;
    }

    public void apply(UserCreatedEvent userCreatedEvent) {
        firstName = userCreatedEvent.getFirstName();
        lastName = userCreatedEvent.getLastName();
        emailAddress = userCreatedEvent.getEmailAddress();
    }
}
