package com.example.presence.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.presence.domain.models.Admin;

public interface IAdminRepository {
    Optional<Admin> findById(UUID id);
    
    Optional<Admin> findByUsername(String username);

    Admin save(Admin admin);
}
