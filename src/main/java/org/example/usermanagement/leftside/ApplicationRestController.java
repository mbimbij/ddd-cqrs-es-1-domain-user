package org.example.usermanagement.leftside;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/users")
public class ApplicationRestController {
    @GetMapping
    public Flux<UserDto> findAll() {
        return Flux.empty();
    }
}
