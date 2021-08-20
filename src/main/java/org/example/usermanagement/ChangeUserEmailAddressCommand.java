package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ChangeUserEmailAddressCommand {
    private final EmailAddress emailAddress;
}
