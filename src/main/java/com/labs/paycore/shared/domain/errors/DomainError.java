package com.labs.paycore.shared.domain.errors;

import org.springframework.http.HttpStatus;

public class DomainError extends RuntimeException {
    private final HttpStatus status;

    public DomainError(String message) {
        super(message);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public DomainError(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}