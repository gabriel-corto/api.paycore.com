package com.labs.paycore.wallet.domain;

import java.math.BigDecimal;

public class Money {
  private long value;

  private Money(long value) {
    this.validate(value);
    this.value = value;
  }

  public static Money toCents(long value) {
    return new Money(value * 100);
  }

  public static Money toUnits(long value) {
    return new Money(value);
  }

  public long getValue() {
    return this.value;
  }

  private void validate(long value) {
    if (value < 0) {
      throw new InvalidAmountException();
    }
  }

  public void add(long value) {
    this.value = this.value + toCent(value);
  }

  public void sub(long value) {
    this.value = this.value - toCent(value);
  }

  private long toCent(long value) {
    return value * 100;
  }
  
  @Override
  public String toString() {
    return String.valueOf(this.value);
  }

  public BigDecimal toUnit() {
    return new BigDecimal(this.value).divide(new BigDecimal(100));
  }
}