package com.labs.paycore.transaction.infra.database.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.labs.paycore.transaction.domain.Transaction;
import com.labs.paycore.transaction.domain.TransactionRepository;

public class InMemoryTransactionRepository implements TransactionRepository {
  private List<Transaction> transactions = new ArrayList<>();

  @Override
  public List<Transaction> findAll() {
    return this.transactions.stream().toList();
  }

  @Override
  public List<Transaction> findAllByWalletId(UUID walletId) {
    return this.transactions.stream().filter(t -> t.getWalletId().equals(walletId)).toList();
  }

  @Override
  public void save(Transaction transaction) {
    this.transactions.add(transaction);
  }
}
