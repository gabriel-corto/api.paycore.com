package com.labs.paycore.wallet.infra.http;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public record WalletDepositRequest(
  @NotNull(message = "amount cannot be null")
  @DecimalMin(value = "0.01", message = "amount must be greater than 0")
  BigDecimal amount
) {}