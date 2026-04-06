package com.labs.paycore.wallet.application;

public record WalletDepositUseCaseInput(
  String amount,
  String userId
) {}
