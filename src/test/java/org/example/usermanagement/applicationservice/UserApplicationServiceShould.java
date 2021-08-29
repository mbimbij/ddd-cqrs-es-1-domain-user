package org.example.usermanagement.applicationservice;

import org.assertj.core.api.Assertions;
import org.example.usermanagement.domain.*;
import org.example.usermanagement.rightside.InMemoryEventStore;
import org.junit.jupiter.api.Test;

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

        UserApplicationService userApplicationService = new UserApplicationService(userFactory,eventStore);

        UserCreatedEvent expectedEvent = new UserCreatedEvent(userId, userName, emailAddress);

        // WHEN
        userApplicationService.createUser(userName, emailAddress);

        // THEN
        Assertions.assertThat(eventStore.getEventsAsCollection()).contains(expectedEvent);
    }
}