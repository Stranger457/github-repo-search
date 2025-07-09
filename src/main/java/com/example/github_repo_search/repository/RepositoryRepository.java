package com.example.github_repo_search.repository;

import com.example.github_repo_search.entity.RepositoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositoryRepository extends JpaRepository<RepositoryEntity, Long> {

    @Query("SELECT r FROM RepositoryEntity r WHERE " +
            "(:language IS NULL OR r.language = :language) AND " +
            "(:minStars IS NULL OR r.stars >= :minStars) " +
            "ORDER BY " +
            "CASE WHEN :sortBy = 'stars' THEN r.stars END DESC, " +
            "CASE WHEN :sortBy = 'forks' THEN r.forks END DESC, " +
            "CASE WHEN :sortBy = 'updated' THEN r.lastUpdated END DESC")
    List<RepositoryEntity> findFiltered(@Param("language") String language,
                                        @Param("minStars") Integer minStars,
                                        @Param("sortBy") String sortBy);
}