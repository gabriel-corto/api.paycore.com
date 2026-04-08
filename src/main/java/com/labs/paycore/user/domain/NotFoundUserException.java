package com.labs.paycore.user.domain;

import com.labs.paycore.shared.domain.errors.DomainError;

public class NotFoundUserException extends DomainError {
  public NotFoundUserException() {
    super("Usuário não encontrado.");
  }
}
