package com.labs.paycore.wallet.application;

public record WalletTransferUseCaseInput(
  String senderWalletId,
  String amount, 
  String recipient
) {}