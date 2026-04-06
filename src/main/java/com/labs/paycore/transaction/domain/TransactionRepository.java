package com.labs.paycore.transaction.domain;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository {
  void save(Transaction transaction);
  List<Transaction> findAll();
  List<Transaction> findAllByWalletId(UUID walletId);
}
