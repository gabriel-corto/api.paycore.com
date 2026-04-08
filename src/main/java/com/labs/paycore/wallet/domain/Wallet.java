package com.labs.paycore.wallet.domain;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Wallet {
  private final UUID id;
  private Money balance;
  private UUID userId;

  private Wallet(UUID id, Money balance, UUID userId) {
    this.id = id;
    this.balance = balance;
    this.userId = userId;
  }
  
  public static Wallet create(UUID userId) {
    return new Wallet(UUID.randomUUID(), Money.fromCents(0), userId);
  }

  public static Wallet restore(UUID id, Money balance, UUID userId) {
    return new Wallet(id, balance, userId);
  }

  public void deposit(long amount) {
    this.balance.add(amount);
  }

  public void withdraw(long amount) {
    var balanceInUnits = this.balance.getValue() / 100;
    
    if(amount > balanceInUnits) {
      throw new InsufficientBalanceException();
    }
    this.balance.sub(amount);
  }
}
