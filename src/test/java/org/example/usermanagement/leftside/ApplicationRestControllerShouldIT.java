package org.example.usermanagement.leftside;

import org.example.usermanagement.applicationservice.UserApplicationService;
import org.example.usermanagement.domain.*;
import org.example.usermanagement.rightside.InMemoryEventStore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@WebFluxTest
class ApplicationRestControllerShouldIT {
    @Autowired
    private WebTestClient webTestClient;

    @SpyBean
    private UserApplicationService userApplicationService;

    @Autowired
    private EventStore eventStore;

    @BeforeEach
    void setUp() {
        eventStore.deleteAll();
    }

    @Test
    void returnEmpty_whenUserNotCreated() {
        Flux<UserResponseDto> users = webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(UserResponseDto.class)
                .getResponseBody();
        StepVerifier.create(users)
                .verifyComplete();
    }

    @Test
    void createUser_whenCallAssociatedEndpoint() {
        // GIVEN
        String usernameString = "username";
        String emailaddressString = "emailaddress";

        // WHEN
        Optional<UserResponseDto> newUserOptional = Optional.ofNullable(webTestClient.post()
                .uri("/users")
                .bodyValue(new CreateUserRequestDto(usernameString, emailaddressString))
                .exchange()
                .expectStatus()
                .isCreated()
                .returnResult(UserResponseDto.class)
                .getResponseBody()
                .blockFirst());

        // THEN
        assertThat(newUserOptional).isNotEmpty();
        assertSoftly(softAssertions -> {
            softAssertions.assertThat(newUserOptional.map(UserResponseDto::getUserName)).hasValue(usernameString);
            softAssertions.assertThat(newUserOptional.map(UserResponseDto::getEmailAddress)).hasValue(emailaddressString);
            softAssertions.assertThat(newUserOptional.map(UserResponseDto::getId)).isNotEmpty();
        });

        UserName expectedUserName = new UserName(usernameString);
        EmailAddress expectedEmailAddress = new EmailAddress(emailaddressString);
        verify(userApplicationService).createUser(eq(expectedUserName), eq(expectedEmailAddress));
    }

    @Test
    void returnAllEntities_afterCreation() {
        // GIVEN
        User user1 = userApplicationService.createUser(new UserName("userName1"), new EmailAddress("emailAddress1"));
        User user2 = userApplicationService.createUser(new UserName("userName2"), new EmailAddress("emailAddress2"));

        // WHEN
        Flux<UserResponseDto> responseFlux = webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(UserResponseDto.class)
                .getResponseBody();

        // THEN
        StepVerifier.create(responseFlux)
                .expectNext(UserResponseDto.from(user1))
                .expectNext(UserResponseDto.from(user2))
                .verifyComplete();
    }

    @Configuration
    @Import(ApplicationRestController.class)
    static class TestConfiguration {
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
        public UserApplicationService userApplicationService(UserFactory userFactory, EventStore eventStore, UserRepository userRepository) {
            return new UserApplicationService(userFactory, userRepository, eventStore);
        }
    }
}