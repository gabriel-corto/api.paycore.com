package com.labs.paycore.transaction.infra.database.postgresql;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.labs.paycore.transaction.domain.Transaction;
import com.labs.paycore.transaction.domain.TransactionRepository;
import com.labs.paycore.transaction.infra.jpa.repositories.JpaTransactionRepository;

@Repository
public class PostgreSQLTransactionRepository implements TransactionRepository {
  private JpaTransactionRepository jpaTransactionRepository;

  public PostgreSQLTransactionRepository(JpaTransactionRepository jpaTransactionRepository) {
    this.jpaTransactionRepository = jpaTransactionRepository;
  }

  @Override
  public void save(Transaction transaction) {
    var transactionModel = PostgreSQLTransactionMapper.toJpaModel(transaction);
    this.jpaTransactionRepository.save(transactionModel);
  }

  @Override
  public List<Transaction> findAll() {
    var transactionModels = this.jpaTransactionRepository.findAll();
    return transactionModels.stream().map(w -> PostgreSQLTransactionMapper.toDomain(w)).toList();
  }

  @Override
  public List<Transaction> findAllByWalletId(UUID walletId) {
    var transactionModels = this.jpaTransactionRepository.findAllByWalletId(walletId);
    return transactionModels.stream().map(w -> PostgreSQLTransactionMapper.toDomain(w)).toList();
  }
}
