package com.example.github_repo_search.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}