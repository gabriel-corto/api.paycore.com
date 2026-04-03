package com.labs.paycore.wallet;

public class Money {
  private Double value;

  private Money(Double value) {
    this.validate(value);
    this.value = value;
  }

  public static Money create(Double value) {
    return new Money(value);
  } 

  public Double getValue() {
    return this.value;
  }

  private void validate(Double value) {
    if(value < 0) {
      throw new Error("Erro! valor negativo.");
    }
  }
}