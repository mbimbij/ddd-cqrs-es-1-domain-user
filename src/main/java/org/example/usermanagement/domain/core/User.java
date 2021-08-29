package org.example.usermanagement.domain.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@AllArgsConstructor
@ToString
public class User {
    private final UserId userId;
    private UserName userName;
    private EmailAddress emailAddress;

    public UserNameChangedEvent changeUsername(UserName newUserName) {
        this.userName = newUserName;
        return new UserNameChangedEvent(userId, newUserName);
    }

    public EmailAddressChangedEvent changeEmailAddress(EmailAddress newEmailAddress) {
        this.emailAddress = newEmailAddress;
        return new EmailAddressChangedEvent(userId, newEmailAddress);
    }
}
