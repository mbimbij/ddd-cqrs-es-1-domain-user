package org.example.usermanagement.write.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.write.domain.FirstName;

@RequiredArgsConstructor
@Getter
public class ChangeUserFirstNameCommand {
    private final FirstName firstName;
}
