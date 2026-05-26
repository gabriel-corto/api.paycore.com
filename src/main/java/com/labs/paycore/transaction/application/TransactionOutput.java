package com.labs.paycore.transaction.application;

import java.math.BigDecimal;

public record TransactionOutput(
  String id,
  BigDecimal amount,
  String operation,
  String type,
  String createdAt,
  String walletId
) {}
