package org.example.usermanagement.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangedFirstNameEvent implements DomainEvent {
    private final FirstName firstname;
}
