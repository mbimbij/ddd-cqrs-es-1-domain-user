package org.example.usermanagement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangedLastNameEvent implements DomainEvent {
    private final LastName lastname;
}
