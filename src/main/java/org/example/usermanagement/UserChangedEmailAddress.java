package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserChangedEmailAddress implements DomainEvent {
    private final EmailAddress emailAddress;
}
