package com.example.presence.domain.service;

import static org.mockito.ArgumentMatchers.any;

import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.presence.domain.models.Admin;
import com.example.presence.domain.repository.IAdminRepository;

public class AdminServiceTest
{
    private IAdminRepository adminRepository;
    private AdminService adminService; 

    @BeforeEach
    void setUp(){
        adminRepository = Mockito.mock(IAdminRepository.class);
        adminService = new AdminService(adminRepository);
    }

    @Test
    @DisplayName("Test should login an already registered admin")
    void shouldLoginRegisteredAdmin() {

       var adminRegistered = new Admin(UUID.randomUUID(), "username");

       Mockito.when(adminRepository.findByUsername("username")).thenReturn(Optional.of(adminRegistered));

       Admin adminLoggedIn = adminService.login("username");

       Assertions.assertThat(adminLoggedIn.getId()).isEqualTo(adminRegistered.getId());
       Assertions.assertThat(adminLoggedIn.getUsername()).isEqualTo(adminRegistered.getUsername());
    }

    @Test
    @DisplayName("Test should login a not registered admin")
    void shouldLoginNotRegisteredAdmin() {

       var adminRegistered = new Admin(UUID.randomUUID(), "username");

       Mockito.when(adminRepository.findByUsername("username")).thenReturn(Optional.empty());
       Mockito.when(adminRepository.save(any(Admin.class))).thenReturn(adminRegistered);

       Admin adminLoggedIn = adminService.login("username");

       Assertions.assertThat(adminLoggedIn).usingRecursiveComparison().isEqualTo(adminRegistered);
    }
}
