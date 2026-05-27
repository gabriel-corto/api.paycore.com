package com.labs.paycore.wallet.application;

import java.util.UUID;

public record WalletDepositUseCaseInput(
  long amount,
  UUID userId
) {}
