package com.labs.paycore.transaction.application;

import java.util.List;

import org.springframework.stereotype.Service;

import com.labs.paycore.transaction.domain.TransactionRepository;

@Service
public class GetAllTransactionsUseCase {
  private TransactionRepository transactionRepository;

  public GetAllTransactionsUseCase(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<TransactionOutput> execute() {
    var transactions = this.transactionRepository.findAll();
    
    return transactions.stream().map(t -> new TransactionOutput(
      t.getId().toString(),
      String.valueOf(t.getAmount().toUnit()),
      t.getOperation().toString(),
      t.getType().toString(),
      t.getCreatedAt().toString(),
      t.getWalletId().toString()
    )).toList();
  }
}
