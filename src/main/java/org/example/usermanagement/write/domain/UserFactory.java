package org.example.usermanagement.write.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class UserFactory {
    private final IGenerateIds IGenerateIds;

    public UserAggregate createUser(FirstName firstName, LastName lastName, EmailAddress emailAddress) {
        UserId userId = getIGenerateIds().generateUserId();
        return new UserAggregate(userId, firstName, lastName, emailAddress);
    }
}
