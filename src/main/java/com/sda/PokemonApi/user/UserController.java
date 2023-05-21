package com.sda.PokemonApi.user;

import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserEntity addMovie( @RequestBody UserEntity user){
        return userService.addUser(user);
    }
}
