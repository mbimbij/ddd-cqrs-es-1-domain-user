package org.example.usermanagement.applicationservice;

import org.example.usermanagement.domain.*;
import org.example.usermanagement.utils.Pair;

import java.util.Optional;

public class UserApplicationService {

    private final UserFactory userFactory;
    private final UserRepository userRepository;
    private final EventStore eventStore;

    public UserApplicationService(UserFactory userFactory, UserRepository userRepository, EventStore eventStore) {
        this.userFactory = userFactory;
        this.userRepository = userRepository;
        this.eventStore = eventStore;
    }

    public void createUser(UserName userName, EmailAddress emailAddress) {
        Pair<User, UserCreatedEvent> userEventPair = userFactory.createUser(userName, emailAddress);
        eventStore.store(userEventPair.getValue2());
    }

    public void changeUsername(UserId userId, UserName newUserName) {
        userRepository.findById(userId)
                .map(user -> user.changeUsername(newUserName))
                .ifPresent(eventStore::store);
    }

    public void changeEmailAddress(UserId userId, EmailAddress newEmailAddress) {
        userRepository.findById(userId)
                .map(user -> user.changeEmailAddress(newEmailAddress))
                .ifPresent(eventStore::store);
    }

    public Optional<User> findUserById(UserId userId) {
        return userRepository.findById(userId);
    }
}
