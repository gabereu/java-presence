package com.example.presence.domain.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.presence.domain.models.Presence;
import com.example.presence.domain.models.User;

public interface IPresenceRepository {
    Optional<Presence> findById(UUID id);

    List<Presence> findByUser(User user);
    List<Presence> findByUserBetweenDates(User user, LocalDateTime fromDate, LocalDateTime toDate);

    List<Presence> findAll();
    List<Presence> findAllBetweenDates(LocalDateTime fromDate, LocalDateTime toDate);
    
    Presence save(Presence presence);
    void delete(Presence presence);
}
