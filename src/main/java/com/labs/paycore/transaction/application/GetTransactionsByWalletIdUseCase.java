package com.labs.paycore.transaction.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labs.paycore.transaction.domain.TransactionRepository;

@Service
public class GetTransactionsByWalletIdUseCase {
  private TransactionRepository transactionRepository;

  public GetTransactionsByWalletIdUseCase(TransactionRepository transactionRepository) {
    this.transactionRepository = transactionRepository;
  }

  public List<TransactionOutput> execute(String walletId) {
    var transactions = this.transactionRepository.findAllByWalletId(UUID.fromString(walletId));
    
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
