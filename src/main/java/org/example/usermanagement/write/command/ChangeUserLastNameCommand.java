package org.example.usermanagement.write.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.write.domain.LastName;

@RequiredArgsConstructor
@Getter
public class ChangeUserLastNameCommand {
    private final LastName lastName;
}
