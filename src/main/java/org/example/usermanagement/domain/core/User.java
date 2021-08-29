package org.example.usermanagement.domain.core;

import lombok.*;

@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@ToString
@EqualsAndHashCode
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

    public void apply(DomainEvent event) {
        if (event instanceof UserCreatedEvent) {
            apply((UserCreatedEvent) event);
        }
    }

    public void apply(UserCreatedEvent userCreatedEvent) {
        this.userName = userCreatedEvent.getUsername();
        this.emailAddress = userCreatedEvent.getEmailAddress();
    }
}
