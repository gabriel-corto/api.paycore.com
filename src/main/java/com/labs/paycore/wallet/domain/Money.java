package com.labs.paycore.wallet.domain;

import java.math.BigDecimal;

public class Money {
  private long value;

  private Money(long value) {
    this.validate(value);
    this.value = value;
  }

  public static Money fromCents(long value) {
    return new Money(value);
  }

  public static Money toCents(long value) {
    return new Money(value * 100);
  }

  public long getValue() {
    return this.value;
  }

  private void validate(long value) {
    if (value < 0) {
      throw new InvalidAmountException();
    }
  }

  public void addCents(long cents) {
    this.value = this.value + cents;
  }

  public void subCents(long cents) {
    this.value = this.value - cents;
  }
  
  @Override
  public String toString() {
    return String.valueOf(this.value);
  }

  public BigDecimal toUnit() {
    return new BigDecimal(this.value).divide(new BigDecimal(100), 2, java.math.RoundingMode.HALF_UP);
  }
}