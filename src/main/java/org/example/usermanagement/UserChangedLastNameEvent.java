package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangedLastNameEvent implements DomainEvent {
    private final LastName lastname;
}
