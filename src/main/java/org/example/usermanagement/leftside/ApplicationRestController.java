package org.example.usermanagement.leftside;

import org.example.usermanagement.applicationservice.UserApplicationService;
import org.example.usermanagement.domain.EmailAddress;
import org.example.usermanagement.domain.User;
import org.example.usermanagement.domain.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class ApplicationRestController {
    @Autowired
    private UserApplicationService userApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<UserResponseDto> createUser(@RequestBody CreateUserRequestDto requestDto) {
        UserName userName = new UserName(requestDto.getUserName());
        EmailAddress emailAddress = new EmailAddress(requestDto.getEmailAddress());
        User user = userApplicationService.createUser(userName, emailAddress);
        return Mono.just(UserResponseDto.from(user));
    }

    @GetMapping
    public Flux<UserResponseDto> findAll() {
        return userApplicationService.findAll()
                .map(UserResponseDto::from);
    }
}
