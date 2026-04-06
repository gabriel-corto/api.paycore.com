package com.labs.paycore.user.domain;

import org.springframework.http.HttpStatus;

import com.labs.paycore.shared.domain.errors.DomainError;

public class UserWithSameEmailException extends DomainError {
  public UserWithSameEmailException() {
    super("Já existe um usuário com o mesmo e-mail.", HttpStatus.CONFLICT);
  }
}
