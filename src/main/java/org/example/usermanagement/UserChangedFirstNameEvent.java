package org.example.usermanagement;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserChangedFirstNameEvent implements DomainEvent {
    private final FirstName firstname;
}
