package com.labs.paycore.auth.domain;

import org.springframework.http.HttpStatus;

import com.labs.paycore.shared.domain.errors.DomainError;

public class InvalidCredentialsException extends DomainError {
  public InvalidCredentialsException() {
    super("Invalid credentials", HttpStatus.UNAUTHORIZED);
  }
}
