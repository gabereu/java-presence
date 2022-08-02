package com.example.presence.domain.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.presence.domain.models.User;
import com.example.presence.domain.repository.IUserRepository;

public class UserServiceTest {

    private IUserRepository userRepository;

    private UserService userService; 

    @BeforeEach
    void setUp(){
        userRepository = Mockito.mock(IUserRepository.class);
        userService = new UserService(userRepository);
    }

    @Test
    @DisplayName("Test should login an already registered user")
    void shouldLoginRegisteredUser() {

       var userRegistered = new User(UUID.randomUUID(), "cpf");

       Mockito.when(userRepository.findByCpf("cpf")).thenReturn(Optional.of(userRegistered));

       User userLoggedIn = userService.login("cpf");

       Assertions.assertThat(userLoggedIn.getId()).isEqualTo(userRegistered.getId());
       Assertions.assertThat(userLoggedIn.getCpf()).isEqualTo(userRegistered.getCpf());
    }

    @Test
    @DisplayName("Test should login a not registered user")
    void shouldLoginNotRegisteredUser() {

       var userRegistered = new User(UUID.randomUUID(), "cpf");

       Mockito.when(userRepository.findByCpf("cpf")).thenReturn(Optional.empty());
       Mockito.when(userRepository.save(any(User.class))).thenReturn(userRegistered);

       User userLoggedIn = userService.login("cpf");

       Assertions.assertThat(userLoggedIn).usingRecursiveComparison().isEqualTo(userRegistered);
    }
}
