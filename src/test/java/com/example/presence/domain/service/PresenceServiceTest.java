package com.example.presence.domain.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import com.example.presence.domain.models.Presence;
import com.example.presence.domain.models.User;
import com.example.presence.domain.repository.IPresenceRepository;

public class PresenceServiceTest {
    
    private IPresenceRepository presenceRepository;
    private PresenceService presenceService; 

    @BeforeEach
    void setUp(){
        presenceRepository = Mockito.mock(IPresenceRepository.class);
        presenceService = new PresenceService(presenceRepository);
    }

    @Test
    @DisplayName("Test should register a presence")
    void testRegisterPresence() {
        var user = new User(UUID.randomUUID(), "cpf");
        
        var date = LocalDateTime.of(2022, 07, 31, 0, 0);
        var dateResponse = LocalDateTime.from(date);

        try(MockedStatic<LocalDateTime> localDateTime = Mockito.mockStatic(LocalDateTime.class, Mockito.CALLS_REAL_METHODS)) {

            localDateTime.when(LocalDateTime::now).thenReturn(dateResponse);
            when(presenceRepository.save(any(Presence.class))).thenAnswer(i -> i.getArgument(0));

            var presence = presenceService.registerPresence(user);
            
            Assertions.assertThat(presence.getUser()).usingRecursiveComparison().isEqualTo(user);
            Assertions.assertThat(presence.getDate()).isEqualTo(date);

            Mockito.verify(presenceRepository, times(1)).save(presence.toBuilder().build());
            Mockito.verifyNoMoreInteractions(presenceRepository);
        }
    }

    @Test
    @DisplayName("Test should register a presence with specific date")
    void testRegisterPresenceWithSpecificDate() {
        var user = new User(UUID.randomUUID(), "cpf");
        
        var date = LocalDateTime.of(2022, 07, 31, 0, 0);
        var dateResponse = LocalDateTime.from(date);

        when(presenceRepository.save(any(Presence.class))).thenAnswer(i -> i.getArgument(0));

        var presence = presenceService.registerPresence(user, date);
        
        Assertions.assertThat(presence.getUser()).usingRecursiveComparison().isEqualTo(user);
        Assertions.assertThat(presence.getDate()).isEqualTo(dateResponse);

        Mockito.verify(presenceRepository, times(1)).save(presence.toBuilder().build());
        Mockito.verifyNoMoreInteractions(presenceRepository);
    }

    @Test
    @DisplayName("Test should change presence date")
    void testChangePresenceDate() {
        var user = new User(UUID.randomUUID(), "cpf");
        var date = LocalDateTime.of(2022, 7, 31, 0, 0);
        var newDate = LocalDateTime.of(2022, 8, 31, 0, 0);
        var dateResponse = LocalDateTime.from(newDate);
        var presence = new Presence(UUID.randomUUID(), date, user);

        when(presenceRepository.save(any(Presence.class))).thenAnswer(i -> i.getArgument(0));

        var presenceUpdated = presenceService.changePresenceDate(presence, newDate);
        
        Assertions.assertThat(presenceUpdated.getId()).isEqualTo(presence.getId());
        Assertions.assertThat(presenceUpdated.getDate()).isEqualTo(dateResponse);

        Mockito.verify(presenceRepository, times(1)).save(presence.toBuilder().date(newDate).build());
        Mockito.verifyNoMoreInteractions(presenceRepository);
    }

    @Test
    void testDeletePresence() {
        var user = new User(UUID.randomUUID(), "cpf");
        var date = LocalDateTime.of(2022, 7, 31, 0, 0);
        var presence = new Presence(UUID.randomUUID(), date, user);

        presenceService.deletePresence(presence);

        Mockito.verify(presenceRepository, times(1)).delete(presence.toBuilder().build());
        Mockito.verifyNoMoreInteractions(presenceRepository);
    }
}
