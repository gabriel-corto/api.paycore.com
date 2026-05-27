package com.labs.paycore.wallet.infra.http;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import com.labs.paycore.wallet.application.WalletDepositUseCaseInput;
import com.labs.paycore.wallet.application.WalletP2PUseCaseInput;

public class WalletInputMapper {
  static WalletDepositUseCaseInput toWalletDepositUseCaseInput(WalletDepositRequest body, UUID userId) {
    return new WalletDepositUseCaseInput(toCents(body.amount()), userId);
  }

  static WalletP2PUseCaseInput toWalletP2PUseCaseInput(WalletP2PRequest body, UUID walletId) {
    return new WalletP2PUseCaseInput(walletId, toCents(body.amount()), body.recipient());
  }

  private static long toCents(BigDecimal amount) {
    return amount.setScale(2, RoundingMode.HALF_UP).movePointRight(2).longValue();
  }
}
