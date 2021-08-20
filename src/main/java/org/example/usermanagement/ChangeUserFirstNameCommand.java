package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ChangeUserFirstNameCommand {
    private final FirstName firstName;
}
