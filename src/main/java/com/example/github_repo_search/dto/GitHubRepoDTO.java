package com.example.github_repo_search.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


import java.time.Instant;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GitHubRepoDTO {
    private Long id;
    private String name;
    private String description;
    private Owner owner;
    private String language;

    @JsonProperty("stargazers_count")
    private int stars;

    @JsonProperty("forks_count")
    private int forks;

    @JsonProperty("updated_at")
    private Instant lastUpdated;

    public void setHtmlUrl(String url) {
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Owner {
        private String login;
        private Long id;
    }
}