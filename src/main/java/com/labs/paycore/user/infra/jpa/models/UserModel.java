package com.labs.paycore.user.infra.jpa.models;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
public class UserModel {
  @Id
  private UUID id;

  private String name;
  private String email;
  private String nif;
  private String password;
}
