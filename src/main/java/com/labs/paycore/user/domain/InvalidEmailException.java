package com.labs.paycore.user.domain;

import com.labs.paycore.shared.domain.errors.DomainError;

public class InvalidEmailException extends DomainError {
  public InvalidEmailException() {
    super("E-mail inválido.");
  }
}
