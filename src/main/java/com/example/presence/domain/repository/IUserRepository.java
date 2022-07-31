package com.example.presence.domain.repository;

import java.util.Optional;
import java.util.UUID;

import com.example.presence.domain.models.User;

public interface IUserRepository {
    Optional<User> findById(UUID id);
    
    Optional<User> findByCpf(String cpf);

    User save(User user);
}
