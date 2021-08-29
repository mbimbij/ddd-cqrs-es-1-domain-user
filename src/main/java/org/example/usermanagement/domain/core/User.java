package org.example.usermanagement.domain.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public class User {
    private final UserId userId;
    private FirstName firstName;
    private LastName lastName;
    private EmailAddress emailAddress;
}
