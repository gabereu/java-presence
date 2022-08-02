package com.example.presence.domain.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.example.presence.domain.models.Presence;
import com.example.presence.domain.models.User;
import com.example.presence.domain.repository.IPresenceRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PresenceService {
    
    private IPresenceRepository presenceRepository;

    public Presence registerPresence(User user) {

        return this.presenceRepository.save(
            new Presence()
            .setUser(user)
            .setDate(LocalDateTime.now())
        );
    }

    public Presence registerPresence(User user, LocalDateTime date ) {

        return this.presenceRepository.save(
            new Presence()
            .setUser(user)
            .setDate(date)
        );
    }

    public Presence changePresenceDate(Presence presence, LocalDateTime toDate){
        return this.presenceRepository.save(presence.setDate(toDate));
    }

    public void deletePresence(Presence presence){
        this.presenceRepository.delete(presence);
    }

}
