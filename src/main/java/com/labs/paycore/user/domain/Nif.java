package com.labs.paycore.user.domain;

public class Nif {
  private String value;
  private String ANGOLAN_NIF_REGEX = "^[0-9]{9}[A-Z]{2}[0-9]{3}";
  
  private Nif(String value) {
    this.validate(value);
    this.value = value;
  } 

  public static Nif create(String value) {
    return new Nif(value);
  }

  public String getValue() {
    return this.value;
  }

  private void validate(String value) {
    if(!value.matches(ANGOLAN_NIF_REGEX)) {
      throw new InvalidNifException();
    }
  }
}
