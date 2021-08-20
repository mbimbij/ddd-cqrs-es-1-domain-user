package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.usermanagement.domain.FirstName;

@RequiredArgsConstructor
@Getter
public class ChangeUserFirstNameCommand {
    private final FirstName firstName;
}
