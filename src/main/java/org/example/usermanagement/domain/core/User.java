package org.example.usermanagement.domain.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class User {
    private final UserId id;
    private UserName userName;
    private EmailAddress emailAddress;

    public UserChangedUserNameEvent changeUsername(UserName newUserName) {
        this.userName = newUserName;
        return new UserChangedUserNameEvent(id, newUserName);
    }

    public UserChangedEmailAddressEvent changeEmailAddress(EmailAddress newEmailAddress) {
        this.emailAddress = newEmailAddress;
        return new UserChangedEmailAddressEvent(id, newEmailAddress);
    }
}
