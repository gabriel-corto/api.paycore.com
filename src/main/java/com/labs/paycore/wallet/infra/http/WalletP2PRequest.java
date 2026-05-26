package com.labs.paycore.wallet.infra.http;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletP2PRequest(
  @NotNull(message = "amount cannot be null!")
  BigDecimal amount, 

  @NotBlank(message = "recipient cannot be null!")
  @NotNull(message = "recipient cannot be null!")
  String recipient
) {}
