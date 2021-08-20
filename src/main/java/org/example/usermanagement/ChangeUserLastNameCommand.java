package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.LastName;

@RequiredArgsConstructor
@Getter
public class ChangeUserLastNameCommand {
    private final LastName lastName;
}
