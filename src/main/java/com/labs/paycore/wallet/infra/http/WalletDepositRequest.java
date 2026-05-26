package com.labs.paycore.wallet.infra.http;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletDepositRequest(
  @NotNull(message = "amount cannot be null!")
  @NotBlank(message = "amount cannot be null!")
  BigDecimal amount
) {
}