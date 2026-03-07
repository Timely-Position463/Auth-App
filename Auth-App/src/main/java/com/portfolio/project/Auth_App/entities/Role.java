package com.portfolio.project.Auth_App.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name="roles")
public class Role {
    @Id
    private UUID id=UUID.randomUUID();
    @Column(unique = true,nullable = false)
    private String name;
}
