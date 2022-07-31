package com.example.presence.domain.models;

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
public class User
{
    private UUID id;

    @Setter
    private String cpf;
}
