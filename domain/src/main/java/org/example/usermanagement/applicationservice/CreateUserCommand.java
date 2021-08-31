package org.example.usermanagement.applicationservice;

import lombok.Value;
import org.example.usermanagement.domain.EmailAddress;
import org.example.usermanagement.domain.UserName;

@Value
public class CreateUserCommand {
    UserName userName;
    EmailAddress emailAddress;
}
