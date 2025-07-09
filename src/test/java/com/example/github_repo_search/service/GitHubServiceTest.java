package com.example.github_repo_search.service;

import com.example.github_repo_search.dto.SearchRequest;
import com.example.github_repo_search.entity.RepositoryEntity;
import com.example.github_repo_search.repository.RepositoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GitHubServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private RepositoryRepository repositoryRepository;

    @InjectMocks
    private GitHubService gitHubService;

    @Test
    void testSearchRepositoriesWithMock() {
        SearchRequest request = new SearchRequest("spring", "java", "stars");

        when(repositoryRepository.save(any())).thenAnswer(i -> i.getArguments()[0]);

        List<RepositoryEntity> result = gitHubService.searchAndSaveRepositories("spring", "java", "stars");

        assertEquals(30, result.size());
    }
}