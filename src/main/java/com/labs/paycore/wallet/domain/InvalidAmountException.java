package com.labs.paycore.wallet.domain;

import com.labs.paycore.shared.domain.errors.DomainError;

public class InvalidAmountException extends DomainError {
  public InvalidAmountException() {
    super("Valor Inválido");
  }
}
