package com.sda.PokemonApi.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;



    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity addUser(UserEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException  {
        return repository.findByNameIgnoreCase(username)
                .map(user -> new User(user.getName(), user.getPassword(), Collections.emptyList()))
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
