package org.example.usermanagement.applicationservice;

import org.example.usermanagement.domain.*;
import org.example.usermanagement.utils.Pair;

public class UserApplicationService {

    private final UserFactory userFactory;
    private final EventStore eventStore;

    public UserApplicationService(UserFactory userFactory, EventStore eventStore) {
        this.userFactory = userFactory;
        this.eventStore = eventStore;
    }

    public void createUser(UserName userName, EmailAddress emailAddress) {
        Pair<User, UserCreatedEvent> userEventPair = userFactory.createUser(userName, emailAddress);
        eventStore.store(userEventPair.getValue2());
    }
}
