package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.EmailAddress;

@RequiredArgsConstructor
@Getter
public class ChangeUserEmailAddressCommand {
    private final EmailAddress emailAddress;
}
