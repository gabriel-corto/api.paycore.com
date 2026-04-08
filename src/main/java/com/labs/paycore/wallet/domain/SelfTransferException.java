package com.labs.paycore.wallet.domain;

import com.labs.paycore.shared.domain.errors.DomainError;

public class SelfTransferException extends DomainError {
  public SelfTransferException() {
    super("Não é possível fazer transferência para sua própria carteira");
  }
}
