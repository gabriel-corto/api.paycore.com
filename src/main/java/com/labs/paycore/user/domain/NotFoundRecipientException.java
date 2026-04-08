package com.labs.paycore.user.domain;

import com.labs.paycore.shared.domain.errors.DomainError;

public class NotFoundRecipientException extends DomainError {
  public NotFoundRecipientException() {
    super("Destinatário não encontrado.");
  }
}
