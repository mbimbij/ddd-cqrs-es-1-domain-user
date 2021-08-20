package org.example.usermanagement;

import lombok.Value;

@Value
public class UserCreatedEvent implements DomainEvent {
    FirstName firstName;
    LastName lastName;
    EmailAddress emailAddress;
}
