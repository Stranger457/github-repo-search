package com.example.github_repo_search.controller;

import com.example.github_repo_search.dto.SearchRequest;
import com.example.github_repo_search.entity.RepositoryEntity;
import com.example.github_repo_search.service.GitHubService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/github", produces = "application/json")
public class GitHubController {

    private final GitHubService githubService;

    public GitHubController(GitHubService githubService) {
        this.githubService = githubService;
    }

    @PostMapping("/search")
    public ResponseEntity<Map<String, Object>> searchRepositories(@RequestBody SearchRequest request) {
        List<RepositoryEntity> savedRepositories = githubService.searchAndSaveRepositories(
                request.getQuery(), request.getLanguage(), request.getSort()
        );

        List<Map<String, Object>> simplifiedRepos = savedRepositories.stream()
                .map(repo -> Map.<String, Object>of(
                        "id", repo.getId(),
                        "name", repo.getName(),
                        "description", repo.getDescription(),
                        "owner", repo.getOwner()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(Map.of(
                "message", "Repositories fetched and saved successfully",
                "repositories", simplifiedRepos
        ));
    }

    @GetMapping("/repositories")
    public ResponseEntity<Map<String, Object>> getRepositories(
            @RequestParam(required = false) String language,
            @RequestParam(required = false) Integer minStars,
            @RequestParam(defaultValue = "stars") String sort
    ) {
        List<RepositoryEntity> filteredRepositories = githubService.getFilteredRepositories(language, minStars, sort);
        return ResponseEntity.ok(Map.of("repositories", filteredRepositories));
    }

    @GetMapping("/repos")
    public ResponseEntity<List<RepositoryEntity>> getReposByUser(@RequestParam String username) {
        List<RepositoryEntity> repos = githubService.searchAndSaveRepositories(username, null, "stars");
        return ResponseEntity.ok(repos);
    }
}