package com.labs.paycore.transaction.infra.http;

public record TransactionResponse(
  String id,
  String amount,
  String operation,
  String type,
  String createdAt,
  String walletId
) {}
