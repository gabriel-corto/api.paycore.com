package com.labs.paycore.user.domain;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
  private final UUID id;
  private String name;
  private Email email;
  private Nif nif;
  private String password;

  private User(UUID id, String name, Email email, Nif nif, String password) {
    this.id = id;
    this.name = name; 
    this.email = email;
    this.nif = nif;
    this.password = password;
  }

  public static User create(String name, Email email, Nif nif, String password) {
    return new User(UUID.randomUUID(), name, email, nif, password);
  }

  public static User restore(UUID id, String name, Email email, Nif nif, String password) {
    return new User(id, name, email, nif, password);
  }
}

