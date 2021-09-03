package org.example.usermanagement.leftside;

import org.example.usermanagement.applicationservice.UserApplicationService;
import org.example.usermanagement.domain.EmailAddress;
import org.example.usermanagement.domain.User;
import org.example.usermanagement.domain.UserId;
import org.example.usermanagement.domain.UserName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class ApplicationRestController {
    @Autowired
    private UserApplicationService userApplicationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody CreateUserRequestDto requestDto) {
        UserName userName = new UserName(requestDto.getUserName());
        EmailAddress emailAddress = new EmailAddress(requestDto.getEmailAddress());
        User user = userApplicationService.createUser(userName, emailAddress);
        return UserResponseDto.from(user);
    }

    @GetMapping
    public List<UserResponseDto> findAll() {
        return userApplicationService.findAll()
                .stream()
                .map(UserResponseDto::from)
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public Optional<UserResponseDto> findById(@Valid @PathVariable("id") Integer value) {
        return userApplicationService.findUserById(UserId.fromValue(value))
                .map(UserResponseDto::from);
    }
}
