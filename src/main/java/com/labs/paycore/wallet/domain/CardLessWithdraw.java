package com.labs.paycore.wallet.domain;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardLessWithdraw {
  private UUID id;
  private Money amount;
  private UUID walletId;
  private Long reference;
  private CardLessWithdrawStatus status;
  private Integer code;
  private LocalDateTime createdAt;
  private LocalDateTime expiresAt;

  private CardLessWithdraw(
      UUID id,
      Money amount,
      UUID walletId,
      Long reference,
      CardLessWithdrawStatus status,
      Integer code,
      LocalDateTime createdAt,
      LocalDateTime expiresAt) {
    this.id = id;
    this.amount = amount;
    this.walletId = walletId;
    this.reference = reference;
    this.status = status;
    this.code = code;
    this.createdAt = createdAt;
    this.expiresAt = expiresAt;
  }

  public static CardLessWithdraw create(long amount, Integer code, UUID walletId) {
    return new CardLessWithdraw(
        UUID.randomUUID(),
        Money.fromCents(amount),
        walletId,
        generateReference(),
        CardLessWithdrawStatus.PENDING,
        code,
        LocalDateTime.now(),
        LocalDateTime.now().plusMinutes(10));
  }

  private static Long generateReference() {
    return ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
  }

  public boolean isExpired() {
    return LocalDateTime.now().isAfter(this.expiresAt);
  }

  public void use() {
    if (isExpired()) {
      throw new ExpiredWithdrawalException();
    }
    this.status = CardLessWithdrawStatus.USED;
  }
}
