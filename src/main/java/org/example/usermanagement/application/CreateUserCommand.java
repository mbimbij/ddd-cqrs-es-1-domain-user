package org.example.usermanagement.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.EmailAddress;
import org.example.usermanagement.domain.FirstName;
import org.example.usermanagement.domain.LastName;

@RequiredArgsConstructor
@Getter
public class CreateUserCommand {
    private final FirstName firstName;
    private final LastName lastName;
    private final EmailAddress emailAddress;
}
