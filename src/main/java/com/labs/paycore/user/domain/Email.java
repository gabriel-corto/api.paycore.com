package com.labs.paycore.user.domain;

public class Email {
  private String value;

  private Email(String value) {
    this.validate(value);
    this.value = value;
  }

  public static Email create(String value) {
    return new Email(value);
  }

  public String getValue() {
    return this.value;
  }

  private void validate(String value) {
    if(!value.contains("@")) {
      throw new InvalidEmailException();
    }
  }
}
