package org.example.usermanagement;

import lombok.Value;

@Value
public class UserCreatedEvent {
    FirstName firstName;
    LastName lastName;
    EmailAddress emailAddress;
}
