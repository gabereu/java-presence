package com.example.presence.infra.hibernate.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@Accessors(chain = true)
@Entity(name = "User")
public class HbUser {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String cpf;
}
