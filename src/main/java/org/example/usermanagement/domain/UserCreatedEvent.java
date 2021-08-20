package org.example.usermanagement.domain;

import lombok.Value;

@Value
public class UserCreatedEvent implements DomainEvent {
    FirstName firstName;
    LastName lastName;
    EmailAddress emailAddress;
}
