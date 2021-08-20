package org.example.usermanagement.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.EmailAddress;

@RequiredArgsConstructor
@Getter
public class ChangeUserEmailAddressCommand {
    private final EmailAddress emailAddress;
}
