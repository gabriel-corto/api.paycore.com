package com.labs.paycore.wallet.application;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labs.paycore.wallet.domain.NotFoundWalletException;
import com.labs.paycore.wallet.domain.WalletRepository;

@Service
public class GetWalletBalanceUseCase {
  private WalletRepository walletRepository; 

  public GetWalletBalanceUseCase(WalletRepository walletRepository) {
    this.walletRepository = walletRepository;
  }

  public GetBalanceUseCaseOutput execute(String walletId) {
    var wallet = this.walletRepository.findById(UUID.fromString(walletId));

    if(wallet.isEmpty()) {
      throw new NotFoundWalletException();
    }
    
    var balanceInCents = wallet.get().getBalance().getValue(); 
    BigDecimal balance = new BigDecimal(balanceInCents).divide(new BigDecimal(100));
    
    return new GetBalanceUseCaseOutput(balance);
  }
}
