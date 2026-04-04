package com.labs.paycore.shared.domain.errors;

import org.springframework.http.HttpStatus;

public class InvalidNifException extends DomainError {
  public InvalidNifException() {
    super("Nif Inválido", HttpStatus.BAD_REQUEST);
  }
}
