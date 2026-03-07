package com.portfolio.project.Auth_App.dto;

import com.portfolio.project.Auth_App.entities.Provider;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String image;

    private Instant createdAt=Instant.now();
    private Instant updatedAt=Instant.now();

    private Provider provider=Provider.LOCAL;
    private Set<RoleDto> roles=new HashSet<>();
}
