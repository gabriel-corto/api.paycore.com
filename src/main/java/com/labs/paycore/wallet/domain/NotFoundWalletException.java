package com.labs.paycore.wallet.domain;

import com.labs.paycore.shared.domain.errors.DomainError;

public class NotFoundWalletException extends DomainError {
  public NotFoundWalletException() {
    super("Carteira não encontrada.");
  }
}
