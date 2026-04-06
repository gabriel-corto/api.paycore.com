package com.labs.paycore.wallet.application;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labs.paycore.transaction.domain.Transaction;
import com.labs.paycore.transaction.domain.TransactionType;
import com.labs.paycore.transaction.domain.TransactionOperation;
import com.labs.paycore.transaction.domain.TransactionRepository;

import com.labs.paycore.wallet.domain.Money;
import com.labs.paycore.wallet.domain.WalletRepository;
import com.labs.paycore.wallet.domain.NotFoundWalletException;

@Service
public class WalletDepositUseCase {
  private WalletRepository walletRepository;
  private TransactionRepository transactionRepository;

  public WalletDepositUseCase(WalletRepository walletRepository, TransactionRepository transactionRepository) {
    this.walletRepository = walletRepository;
    this.transactionRepository = transactionRepository;
  }

  public void execute(WalletDepositUseCaseInput input) {
    UUID userId = UUID.fromString(input.userId());
    var wallet = this.walletRepository.findByUserId(userId);

    if(wallet.isEmpty()) {
      throw new NotFoundWalletException();
    }

    var parsedAmount = new BigDecimal(input.amount());
    long amount = parsedAmount.longValue();
    wallet.get().deposit(amount);
    
    var transaction = Transaction.create(
      Money.fromUnits(amount), 
      TransactionOperation.DEPOSIT, 
      TransactionType.INCOME,
      wallet.get().getId()
    );

    this.walletRepository.save(wallet.get());
    this.transactionRepository.save(transaction);
  }
}
