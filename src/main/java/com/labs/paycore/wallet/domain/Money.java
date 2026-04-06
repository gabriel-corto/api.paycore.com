package com.labs.paycore.wallet.domain;

public class Money {
  private long value;

  private Money(long value) {
    this.validate(value);
    this.value = value;
  }

  public static Money fromUnits(long value) {
    return new Money(value * 100);
  }

  public static Money fromCents(long value) {
    return new Money(value);
  } 

  public long getValue() {
    return this.value;
  }

  private void validate(long value) {
    if(value < 0) {
      throw new Error("Erro! valor negativo.");
    }
  }

  public void add(long value) {
    this.value = this.value + toCents(value);
  }

  public void sub(long value) {
    this.value = this.value - toCents(value);
  }

  private long toCents(long value) {
    return value * 100;
  }

  @Override
  public String toString() {
    return String.valueOf(this.value);
  }
}