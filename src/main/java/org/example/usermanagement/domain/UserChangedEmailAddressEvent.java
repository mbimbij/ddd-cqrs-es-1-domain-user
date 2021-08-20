package org.example.usermanagement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserChangedEmailAddressEvent implements DomainEvent {
    private final EmailAddress emailAddress;
}
