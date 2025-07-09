package com.example.github_repo_search.dto;

import lombok.Data;

@Data
public class SearchRequest {
    private String query;
    private String language;
    private String sort;

    public SearchRequest(String spring, String java, String stars) {
    }
}