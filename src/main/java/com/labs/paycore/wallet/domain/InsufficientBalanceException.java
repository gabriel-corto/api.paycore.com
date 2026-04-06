package com.labs.paycore.wallet.domain;

import org.springframework.http.HttpStatus;

import com.labs.paycore.shared.domain.errors.DomainError;

public class InsufficientBalanceException extends DomainError {
  public InsufficientBalanceException() {
    super("Saldo insuficiente.", HttpStatus.BAD_REQUEST);
  }
}
