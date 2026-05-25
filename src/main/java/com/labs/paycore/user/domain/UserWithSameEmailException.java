package com.labs.paycore.user.domain;

import org.springframework.http.HttpStatus;

import com.labs.paycore.shared.domain.errors.DomainError;

public class UserWithSameEmailException extends DomainError {
  public UserWithSameEmailException() {
    super("User with same email", HttpStatus.CONFLICT);
  }
}
