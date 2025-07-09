package com.example.github_repo_search.service;

import com.example.github_repo_search.entity.RepositoryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GitHubServiceIntegrationTest {

    @Autowired
    private GitHubService gitHubService;

    @Test
    void shouldReturnRepositoriesWhenUserExists() {
        List<RepositoryEntity> repos = gitHubService.fetchReposByUser("octocat");
        assertFalse(repos.isEmpty(), "Expected at least one repo for 'octocat'");
    }
}