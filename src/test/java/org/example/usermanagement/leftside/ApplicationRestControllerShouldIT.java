package org.example.usermanagement.leftside;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@WebFluxTest
class ApplicationRestControllerShouldIT {
    @Autowired
    private WebTestClient webTestClient;

    @Test
    void returnEmpty_whenUserNotCreated() {
        Flux<UserDto> users = webTestClient.get()
                .uri("/users")
                .exchange()
                .expectStatus()
                .isOk()
                .returnResult(UserDto.class)
                .getResponseBody();
        StepVerifier.create(users)
                .verifyComplete();
    }
}