package com.example.github_repo_search.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.github_repo_search.dto.GitHubRepoDTO;
import com.example.github_repo_search.entity.RepositoryEntity;
import com.example.github_repo_search.exception.ApiException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.example.github_repo_search.repository.RepositoryRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class GitHubService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final RepositoryRepository repositoryRepo;
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public GitHubService(RepositoryRepository repositoryRepo) {
        this.repositoryRepo = repositoryRepo;
    }

    public List<RepositoryEntity> searchAndSaveRepositories(String query, String language, String sort) {
        String url = UriComponentsBuilder.fromHttpUrl("https://api.github.com/search/repositories")
                .queryParam("q", query + (language != null ? "+language:" + language : ""))
                .queryParam("sort", sort)
                .queryParam("order", "desc")
                .build().toUriString();

        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode items = response.getBody().get("items");

            if (items == null || !items.isArray()) return Collections.emptyList();

            List<RepositoryEntity> repos = new ArrayList<>();
            for (JsonNode item : items) {
                GitHubRepoDTO dto = mapper.treeToValue(item, GitHubRepoDTO.class);
                RepositoryEntity entity = new RepositoryEntity(
                        dto.getId(), dto.getName(), dto.getDescription(),
                        dto.getOwner().getLogin(), dto.getLanguage(),
                        dto.getStars(), dto.getForks(), dto.getLastUpdated()
                );
                repos.add(repositoryRepo.save(entity));
            }

            return repos;
        } catch (Exception e) {
            throw new ApiException("Error fetching data from GitHub: " + e.getMessage());
        }
    }

    public List<RepositoryEntity> getFilteredRepositories(String language, Integer minStars, String sortBy) {
        return repositoryRepo.findFiltered(language, minStars, sortBy);
    }

    public List<RepositoryEntity> fetchReposByUser(String username) {
        String url = "https://api.github.com/users/" + username + "/repos";

        try {
            ResponseEntity<JsonNode> response = restTemplate.getForEntity(url, JsonNode.class);
            JsonNode items = response.getBody();

            if (items == null || !items.isArray()) return Collections.emptyList();

            List<RepositoryEntity> repos = new ArrayList<>();
            for (JsonNode item : items) {
                GitHubRepoDTO dto = mapper.treeToValue(item, GitHubRepoDTO.class);
                RepositoryEntity entity = new RepositoryEntity(
                        dto.getId(), dto.getName(), dto.getDescription(),
                        dto.getOwner().getLogin(), dto.getLanguage(),
                        dto.getStars(), dto.getForks(), dto.getLastUpdated()
                );
                repos.add(entity);
            }

            return repos;
        } catch (Exception e) {
            throw new ApiException("Error fetching repositories for user '" + username + "': " + e.getMessage());
        }
    }
}