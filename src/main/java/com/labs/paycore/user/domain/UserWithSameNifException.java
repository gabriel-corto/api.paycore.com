package com.labs.paycore.user.domain;

import org.springframework.http.HttpStatus;

import com.labs.paycore.shared.domain.errors.DomainError;

public class UserWithSameNifException extends DomainError {
  public UserWithSameNifException() {
    super("Já existe um usuário com o mesmo NIF.", HttpStatus.CONFLICT);
  }
}
