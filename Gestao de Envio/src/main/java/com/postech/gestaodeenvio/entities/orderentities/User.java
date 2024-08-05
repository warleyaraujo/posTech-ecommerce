package com.postech.gestaodeenvio.entities.orderentities;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "\"user\"")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private UUID id;
    private String protocol;
    private String firstname;
    private String lastname;
    private String email;
    private String picture;
    private String thumbnail;
    private String document;
    private LocalDateTime birthdate;
    private LocalDateTime emailConfirmedAt;
    private String emailAlternative;
    private Boolean imported;
    private LocalDateTime accessAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer appId;

    // Getters and setters
}
