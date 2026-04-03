package com.labs.paycore.wallet;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Wallet {
  private final UUID id;
  private Money balance;
  private UUID userId;

  public void setUserId(UUID userId) {
    this.userId = userId;
  }

  private Wallet(UUID userId) {
    this.id = UUID.randomUUID();
    this.balance = Money.create(0.00);
    this.userId = userId;
  }
  
  public static Wallet create(UUID userId) {
    return new Wallet(userId);
  }
}
