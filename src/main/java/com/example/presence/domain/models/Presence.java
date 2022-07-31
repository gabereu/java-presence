package com.example.presence.domain.models;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Presence
{
    UUID id;

    @Setter
    LocalDateTime date;

    @Setter
    User user;
}
