package com.labs.paycore.shared.domain.errors;

public class NotFoundUserException extends DomainError {
  public NotFoundUserException() {
    super("User not found");
  }
}
