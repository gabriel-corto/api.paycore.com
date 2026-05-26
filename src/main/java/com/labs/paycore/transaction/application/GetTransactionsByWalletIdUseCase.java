package com.labs.paycore.transaction.application;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labs.paycore.transaction.domain.TransactionRepository;
import com.labs.paycore.wallet.domain.NotFoundWalletException;
import com.labs.paycore.wallet.domain.WalletRepository;

@Service
public class GetTransactionsByWalletIdUseCase {
  private TransactionRepository transactionRepository;
  private WalletRepository walletRepository;

  public GetTransactionsByWalletIdUseCase(
    TransactionRepository transactionRepository,
    WalletRepository walletRepository
  ) 
  {
    this.transactionRepository = transactionRepository;
    this.walletRepository = walletRepository;
  }

  public List<TransactionOutput> execute(UUID walletId) {

    var wallet = this.walletRepository.findById(walletId);

    if(wallet.isEmpty()) {
      throw new NotFoundWalletException();
    }

    var transactions = this.transactionRepository.findAllByWalletId(walletId);
    
    return transactions.stream().map(t -> new TransactionOutput(
      t.getId().toString(),
      t.getAmount().toUnit(),
      t.getOperation().toString(),
      t.getType().toString(),
      t.getCreatedAt().toString(),
      t.getWalletId().toString()
    )).toList();
  }
}
