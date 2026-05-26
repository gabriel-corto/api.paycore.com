package com.labs.paycore.wallet.application;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletDepositUseCaseInput(
  BigDecimal amount,
  UUID userId
) {}
