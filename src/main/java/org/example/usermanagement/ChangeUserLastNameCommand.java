package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ChangeUserLastNameCommand {
    private final LastName lastName;
}
