package org.example.usermanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final UserId id;
    private FirstName firstName;
    private LastName lastName;
    private EmailAddress emailAddress;
}
