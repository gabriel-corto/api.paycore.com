package com.labs.paycore.wallet.application;

public record WalletP2PUseCaseInput(
  String senderWalletId,
  String amount, 
  String recipient
) {}