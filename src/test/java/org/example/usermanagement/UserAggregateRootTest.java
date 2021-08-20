package org.example.usermanagement;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

public class UserAggregateRootTest {

    private UserId id;
    private UserAggregateRoot userAggregateRoot;

    @BeforeEach
    void setUp() {
        id = new UserId("id");
        userAggregateRoot = new UserAggregateRoot(id);
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
        userAggregateRoot.apply(userCreatedEvent);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(userAggregateRoot.getFirstName()).isEqualTo(new FirstName("firstname"));
            softAssertions.assertThat(userAggregateRoot.getLastName()).isEqualTo(new LastName("lastname"));
            softAssertions.assertThat(userAggregateRoot.getEmailAddress()).isEqualTo(new EmailAddress("emailaddress"));
            softAssertions.assertThat(userAggregateRoot.isDeleted()).isFalse();
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
        userAggregateRoot.apply(userCreatedEvent);
        userAggregateRoot.apply(userChangedFirstNameEvent);
        userAggregateRoot.apply(userChangedFirstNameEvent2);
        userAggregateRoot.apply(userChangedLastNameEvent);
        userAggregateRoot.apply(userChangedEmailAddressEvent);
        userAggregateRoot.apply(userDeletedEvent);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(userAggregateRoot.getFirstName()).isEqualTo(new FirstName("firstname3"));
            softAssertions.assertThat(userAggregateRoot.getLastName()).isEqualTo(new LastName("lastname2"));
            softAssertions.assertThat(userAggregateRoot.getEmailAddress()).isEqualTo(new EmailAddress("emailaddress2"));
            softAssertions.assertThat(userAggregateRoot.isDeleted()).isTrue();
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
        domainEvents.add(userAggregateRoot.handle(createUserCommand));
        domainEvents.add(userAggregateRoot.handle(changeUserFirstNameCommand));
        domainEvents.add(userAggregateRoot.handle(changeUserLastNameCommand));
        domainEvents.add(userAggregateRoot.handle(changeUserEmailAddressCommand));
        domainEvents.add(userAggregateRoot.handle(deleteUserCommand));

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(userAggregateRoot.getFirstName()).isEqualTo(new FirstName("firstname2"));
            softAssertions.assertThat(userAggregateRoot.getLastName()).isEqualTo(new LastName("lastname2"));
            softAssertions.assertThat(userAggregateRoot.getEmailAddress()).isEqualTo(new EmailAddress("emailaddress2"));
            softAssertions.assertThat(userAggregateRoot.isDeleted()).isTrue();
            softAssertions.assertThat(domainEvents).hasSize(5);
            softAssertions.assertThat(domainEvents.get(0)).isInstanceOf(UserCreatedEvent.class);
            softAssertions.assertThat(domainEvents.get(1)).isInstanceOf(UserChangedFirstNameEvent.class);
            softAssertions.assertThat(domainEvents.get(2)).isInstanceOf(UserChangedLastNameEvent.class);
            softAssertions.assertThat(domainEvents.get(3)).isInstanceOf(UserChangedEmailAddressEvent.class);
            softAssertions.assertThat(domainEvents.get(4)).isInstanceOf(UserDeletedEvent.class);
        });
    }
}
