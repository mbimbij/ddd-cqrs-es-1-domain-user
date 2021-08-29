package org.example.usermanagement.applicationservice;

import org.example.usermanagement.domain.*;
import org.example.usermanagement.rightside.InMemoryEventStore;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.example.usermanagement.domain.UserId.nextUserId;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class UserApplicationServiceShould {
    @Test
    void createUserAndSendEvent_whenCreateUser() {
        // GIVEN
        UserId userId = nextUserId();
        UserName userName = new UserName("username");
        EmailAddress emailAddress = new EmailAddress("emailaddress");

        UserFactory userFactory = spy(new UserFactory());
        doReturn(userId).when(userFactory).getNextUserId();
        InMemoryEventStore eventStore = new InMemoryEventStore();
        UserRepository userRepository = new UserRepository(eventStore);

        UserApplicationService userApplicationService = new UserApplicationService(userFactory, userRepository, eventStore);

        UserCreatedEvent expectedEvent = new UserCreatedEvent(userId, userName, emailAddress);

        // WHEN
        userApplicationService.createUser(userName, emailAddress);

        // THEN
        assertThat(eventStore.getEventsAsCollection()).contains(expectedEvent);
    }

    @Test
    void handleAWholeSequenceOfCommands_andRetrieveEntityAfterward() {
        // GIVEN
        UserId userId = nextUserId();
        UserName userName = new UserName("username");
        EmailAddress emailAddress = new EmailAddress("emailaddress");

        UserFactory userFactory = spy(new UserFactory());
        doReturn(userId).when(userFactory).getNextUserId();
        InMemoryEventStore eventStore = new InMemoryEventStore();
        UserRepository userRepository = new UserRepository(eventStore);

        UserApplicationService userApplicationService = new UserApplicationService(userFactory, userRepository, eventStore);

        UserName newusername = new UserName("newusername");
        EmailAddress newemailaddress = new EmailAddress("newemailaddress");

        UserCreatedEvent expectedEvent1 = new UserCreatedEvent(userId, userName, emailAddress);
        UserChangedUserNameEvent expectedEvent2 = new UserChangedUserNameEvent(userId, newusername);
        UserChangedEmailAddressEvent expectedEvent3 = new UserChangedEmailAddressEvent(userId, newemailaddress);

        User expectedUser = userFactory.createUser(newusername, newemailaddress).getValue1();
        assertThat(expectedUser.getId()).isEqualTo(userId);

        // WHEN
        userApplicationService.createUser(userName, emailAddress);
        userApplicationService.changeUsername(userId, newusername);
        userApplicationService.changeEmailAddress(userId, newemailaddress);
        Optional<User> userOptional = userApplicationService.findUserById(userId);

        // THEN
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(eventStore.getEventsAsCollection()).containsExactly(expectedEvent1, expectedEvent2, expectedEvent3);
            softAssertions.assertThat(userOptional).hasValue(expectedUser);
        });
    }
}