package org.example.usermanagement.leftside;

import lombok.Value;

@Value
public class CreateUserRequestDto {
    String userName;
    String emailAddress;
}
