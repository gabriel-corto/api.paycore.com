package com.labs.paycore.transaction.application;

public record TransactionOutput(
  String id,
  String amount,
  String operation,
  String type,
  String createdAt,
  String walletId
) {
  
}
