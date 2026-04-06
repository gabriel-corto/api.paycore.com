package com.labs.paycore.user.domain;

import org.springframework.http.HttpStatus;

import com.labs.paycore.shared.domain.errors.DomainError;

public class InvalidNifException extends DomainError {
  public InvalidNifException() {
    super("Nif Inválido", HttpStatus.BAD_REQUEST);
  }
}
