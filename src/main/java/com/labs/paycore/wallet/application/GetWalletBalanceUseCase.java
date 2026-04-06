package com.labs.paycore.wallet.application;

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

  public GetBalanceUseCaseOutput execute(String userId) {
    var wallet = this.walletRepository.findByUserId(UUID.fromString(userId));

    if(wallet.isEmpty()) {
      throw new NotFoundWalletException();
    }

    var balanceInCents = wallet.get().getBalance().getValue(); 
    var balance = balanceInCents / 100;

    System.out.println(balanceInCents);
    System.out.println(String.valueOf(balance));

    return new GetBalanceUseCaseOutput(String.valueOf(balance));
  }
}
