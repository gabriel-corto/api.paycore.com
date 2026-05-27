package com.labs.paycore.wallet.application;

import java.util.UUID;

public record WalletP2PUseCaseInput(
  UUID senderWalletId,
  long amount,
  String recipient
) {}