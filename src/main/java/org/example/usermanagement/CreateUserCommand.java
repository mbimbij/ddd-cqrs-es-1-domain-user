package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class CreateUserCommand {
    private final FirstName firstName;
    private final LastName lastName;
    private final EmailAddress emailAddress;
}
