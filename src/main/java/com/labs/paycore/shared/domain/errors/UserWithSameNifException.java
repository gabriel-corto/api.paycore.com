package com.labs.paycore.shared.domain.errors;

import org.springframework.http.HttpStatus;

public class UserWithSameNifException extends DomainError {
  public UserWithSameNifException() {
    super("Já existe um usuário com o mesmo NIF.", HttpStatus.CONFLICT);
  }
}
