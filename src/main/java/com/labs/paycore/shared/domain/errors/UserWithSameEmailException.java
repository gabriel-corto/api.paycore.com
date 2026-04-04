package com.labs.paycore.shared.domain.errors;

import org.springframework.http.HttpStatus;

public class UserWithSameEmailException extends DomainError {
  public UserWithSameEmailException() {
    super("Já existe um usuário com o mesmo e-mail.", HttpStatus.CONFLICT);
  }
}
