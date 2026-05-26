package com.labs.paycore.wallet.infra.http;

import java.util.UUID;

import com.labs.paycore.wallet.application.WalletDepositUseCaseInput;
import com.labs.paycore.wallet.application.WalletP2PUseCaseInput;

public class WalletInputMapper {
  static WalletDepositUseCaseInput toWalletDepositUseCaseInput(WalletDepositRequest body, UUID userId) {
    return new WalletDepositUseCaseInput(body.amount(), userId);
  }

  static WalletP2PUseCaseInput toWalletP2PUseCaseInput(WalletP2PRequest body, UUID walletId) {
    return new WalletP2PUseCaseInput(walletId, body.amount(), body.recipient());
  }
}
