package com.portfolio.project.Auth_App.entities;

import jakarta.persistence.*;
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
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;
    @Column(name="username",length = 500)
    private String name;
    @Column(name="user_email", unique = true,length = 300)
    private String email;
    private String password;
    private String image;
    private boolean enable=true;
    private Instant createdAt=Instant.now();
    private Instant updatedAt=Instant.now();

    @Enumerated(EnumType.STRING)
    private Provider provider=Provider.LOCAL;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Set<Role> roles=new HashSet<>();

    @PrePersist
    private void onCreate(){
        Instant now=Instant.now();
        if(createdAt==null)createdAt=now;
        updatedAt=now;
    }

    @PreUpdate
    private void onUpdate(){
        updatedAt=Instant.now();
    }
}
