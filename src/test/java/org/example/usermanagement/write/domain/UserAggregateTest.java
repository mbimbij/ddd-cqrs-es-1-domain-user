package org.example.usermanagement.write.domain;

import org.example.usermanagement.write.command.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class UserAggregateTest {

    private UserId id;
    private UserAggregate userAggregate;

    @BeforeEach
    void setUp() {
        id = new UserId("id");
        userAggregate = new UserAggregate(id);
    }

    @Test
    void canApply1EventToUser() {
        // GIVEN
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
                new FirstName("firstname"),
                new LastName("lastname"),
                new EmailAddress("emailaddress")
        );

        // WHEN
        userAggregate.apply(userCreatedEvent);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(userAggregate.getFirstName()).isEqualTo(new FirstName("firstname"));
            softAssertions.assertThat(userAggregate.getLastName()).isEqualTo(new LastName("lastname"));
            softAssertions.assertThat(userAggregate.getEmailAddress()).isEqualTo(new EmailAddress("emailaddress"));
            softAssertions.assertThat(userAggregate.isDeleted()).isFalse();
        });
    }

    @Test
    void canApplyMultipleEventsToUser() {
        // GIVEN
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent(
                new FirstName("firstname"),
                new LastName("lastname"),
                new EmailAddress("emailaddress")
        );
        UserChangedFirstNameEvent userChangedFirstNameEvent = new UserChangedFirstNameEvent(new FirstName("firstname2"));
        UserChangedFirstNameEvent userChangedFirstNameEvent2 = new UserChangedFirstNameEvent(new FirstName("firstname3"));
        UserChangedLastNameEvent userChangedLastNameEvent = new UserChangedLastNameEvent(new LastName("lastname2"));
        UserChangedEmailAddressEvent userChangedEmailAddressEvent = new UserChangedEmailAddressEvent(new EmailAddress("emailaddress2"));
        UserDeletedEvent userDeletedEvent = new UserDeletedEvent();

        // WHEN
        userAggregate.apply(userCreatedEvent);
        userAggregate.apply(userChangedFirstNameEvent);
        userAggregate.apply(userChangedFirstNameEvent2);
        userAggregate.apply(userChangedLastNameEvent);
        userAggregate.apply(userChangedEmailAddressEvent);
        userAggregate.apply(userDeletedEvent);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(userAggregate.getFirstName()).isEqualTo(new FirstName("firstname3"));
            softAssertions.assertThat(userAggregate.getLastName()).isEqualTo(new LastName("lastname2"));
            softAssertions.assertThat(userAggregate.getEmailAddress()).isEqualTo(new EmailAddress("emailaddress2"));
            softAssertions.assertThat(userAggregate.isDeleted()).isTrue();
        });
    }

    @Test
    void canCallCommands() {
        // GIVEN
        List<DomainEvent> domainEvents = new ArrayList<>();
        CreateUserCommand createUserCommand = new CreateUserCommand(new FirstName("firstname"),
                new LastName("lastname"),
                new EmailAddress("emailaddress"));
        ChangeUserFirstNameCommand changeUserFirstNameCommand = new ChangeUserFirstNameCommand(new FirstName("firstname2"));
        ChangeUserLastNameCommand changeUserLastNameCommand = new ChangeUserLastNameCommand(new LastName("lastname2"));
        ChangeUserEmailAddressCommand changeUserEmailAddressCommand = new ChangeUserEmailAddressCommand(new EmailAddress("emailaddress2"));
        DeleteUserCommand deleteUserCommand = new DeleteUserCommand();

        // WHEN
        domainEvents.add(userAggregate.handle(createUserCommand));
        domainEvents.add(userAggregate.handle(changeUserFirstNameCommand));
        domainEvents.add(userAggregate.handle(changeUserLastNameCommand));
        domainEvents.add(userAggregate.handle(changeUserEmailAddressCommand));
        domainEvents.add(userAggregate.handle(deleteUserCommand));

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(userAggregate.getFirstName()).isEqualTo(new FirstName("firstname2"));
            softAssertions.assertThat(userAggregate.getLastName()).isEqualTo(new LastName("lastname2"));
            softAssertions.assertThat(userAggregate.getEmailAddress()).isEqualTo(new EmailAddress("emailaddress2"));
            softAssertions.assertThat(userAggregate.isDeleted()).isTrue();
            softAssertions.assertThat(domainEvents).hasSize(5);
            softAssertions.assertThat(domainEvents.get(0)).isInstanceOf(UserCreatedEvent.class);
            softAssertions.assertThat(domainEvents.get(1)).isInstanceOf(UserChangedFirstNameEvent.class);
            softAssertions.assertThat(domainEvents.get(2)).isInstanceOf(UserChangedLastNameEvent.class);
            softAssertions.assertThat(domainEvents.get(3)).isInstanceOf(UserChangedEmailAddressEvent.class);
            softAssertions.assertThat(domainEvents.get(4)).isInstanceOf(UserDeletedEvent.class);
        });
    }
}
