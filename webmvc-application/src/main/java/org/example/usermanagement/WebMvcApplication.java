package org.example.usermanagement;

import org.example.usermanagement.applicationservice.UserApplicationService;
import org.example.usermanagement.domain.EventStore;
import org.example.usermanagement.domain.UserFactory;
import org.example.usermanagement.domain.UserRepository;
import org.example.usermanagement.rightside.InMemoryEventStore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebMvcApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebMvcApplication.class, args);
    }

    @Bean
    public UserFactory userFactory() {
        return new UserFactory();
    }

    @Bean
    public EventStore eventStore() {
        return new InMemoryEventStore();
    }

    @Bean
    public UserRepository userRepository(EventStore eventStore) {
        return new UserRepository(eventStore);
    }

    @Bean
    public UserApplicationService userApplicationService(UserFactory userFactory, UserRepository userRepository, EventStore eventStore) {
        return new UserApplicationService(userFactory, userRepository, eventStore);
    }
}
