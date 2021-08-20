package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserChangedEmailAddressEvent implements DomainEvent {
    private final EmailAddress emailAddress;
}
