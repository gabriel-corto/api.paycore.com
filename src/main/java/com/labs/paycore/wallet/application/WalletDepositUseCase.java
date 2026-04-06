package com.labs.paycore.wallet.application;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labs.paycore.wallet.domain.NotFoundWalletException;
import com.labs.paycore.wallet.domain.WalletRepository;

@Service
public class WalletDepositUseCase {
  private WalletRepository walletRepository;

  public WalletDepositUseCase(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
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
    this.walletRepository.save(wallet.get());
  }
}
