package com.labs.paycore.wallet.application;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletP2PUseCaseInput(
  UUID senderWalletId,
  BigDecimal amount,
  String recipient
) {}