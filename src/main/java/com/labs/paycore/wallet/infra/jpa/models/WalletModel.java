package com.labs.paycore.wallet.infra.jpa.models;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "wallets")
public class WalletModel {
  @Id
  private UUID id;
  
  private long balance;

  @Column(name = "user_id")
  private UUID userId;
}
