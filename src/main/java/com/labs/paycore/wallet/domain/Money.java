package com.labs.paycore.wallet.domain;


public class Money {
  private final long value;

  private Money(long value) {
    this.validate(value);
    this.value = value;
  }

  public static Money toCents(long value) {
    return new Money(value * 100);
  } 

  public static Money fromCents(long value) {
    return new Money(value / 100);
  }

  public long getValue() {
    return this.value;
  }

  private void validate(long value) {
    if(value < 0) {
      throw new Error("Erro! valor negativo.");
    }
  }
}