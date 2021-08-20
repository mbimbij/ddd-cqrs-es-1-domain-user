package org.example.usermanagement.write.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.write.domain.EmailAddress;
import org.example.usermanagement.write.domain.FirstName;
import org.example.usermanagement.write.domain.LastName;

@RequiredArgsConstructor
@Getter
public class CreateUserCommand {
    private final FirstName firstName;
    private final LastName lastName;
    private final EmailAddress emailAddress;
}
