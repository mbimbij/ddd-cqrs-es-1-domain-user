package org.example.usermanagement.write.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.write.domain.EmailAddress;

@RequiredArgsConstructor
@Getter
public class ChangeUserEmailAddressCommand {
    private final EmailAddress emailAddress;
}
