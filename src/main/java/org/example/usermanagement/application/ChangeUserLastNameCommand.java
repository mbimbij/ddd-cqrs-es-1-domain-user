package org.example.usermanagement.application;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.LastName;

@RequiredArgsConstructor
@Getter
public class ChangeUserLastNameCommand {
    private final LastName lastName;
}
