package com.example.github_repo_search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryEntity {

    @Id
    private Long id;

    @Column(length = 300)
    private String name;

    @Column(length = 2000)
    private String description;

    @Column(length = 255)
    private String owner;

    @Column(length = 100)
    private String language;

    private int stars;
    private int forks;

    @Column(name = "last_updated")
    private Instant lastUpdated;
}


