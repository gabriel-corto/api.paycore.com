package com.labs.paycore.transaction.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import com.labs.paycore.wallet.domain.Money;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
  private UUID id; 
  private Money amount;
  private TransactionOperation operation;
  private TransactionType type;
  private LocalDateTime createdAt;
  private UUID walletId;

  private Transaction(
    UUID id, 
    Money amount, 
    TransactionOperation operation, 
    TransactionType type, 
    LocalDateTime createdAt,
    UUID walletId
  ) {
    this.id = id;
    this.amount = amount;
    this.operation = operation;
    this.type = type;
    this.createdAt = createdAt;
    this.walletId = walletId;
  }

  public static Transaction create(
    Money amount, 
    TransactionOperation operation, 
    TransactionType type, 
    UUID walletId
  ) {
    return new Transaction(UUID.randomUUID(), amount, operation, type, LocalDateTime.now(), walletId);
  }

  public static Transaction restore(
    UUID id, 
    Money amount, 
    TransactionOperation operation, 
    TransactionType type, 
    LocalDateTime createdAt,
    UUID walletId
  ) {
    return new Transaction(id, amount, operation, type, createdAt, walletId);
  }
}