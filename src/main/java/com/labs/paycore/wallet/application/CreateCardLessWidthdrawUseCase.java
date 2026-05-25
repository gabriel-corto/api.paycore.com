package com.labs.paycore.wallet.application;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labs.paycore.wallet.domain.CardLessWithdraw;
import com.labs.paycore.wallet.domain.CardLessWithdrawRepository;
import com.labs.paycore.wallet.domain.NotFoundWalletException;
import com.labs.paycore.wallet.domain.WalletRepository;

@Service
public class CreateCardLessWidthdrawUseCase {
  private CardLessWithdrawRepository cardLessWithdrawRepository;
  private WalletRepository walletRepository;

  public CreateCardLessWidthdrawUseCase(
      CardLessWithdrawRepository cardLessWithdrawRepository,
      WalletRepository walletRepository) {
    this.cardLessWithdrawRepository = cardLessWithdrawRepository;
  }

  public void execute(CardLessWidthdrawUseCaseInput input) {
    var amount = Long.parseLong(input.amount());
    var code = input.code();
    var walletId = UUID.fromString(input.walletId());

    var wallet = this.walletRepository.findById(walletId);

    if (wallet.isEmpty()) {
      throw new NotFoundWalletException();
    }

    var cardLessWithdraw = CardLessWithdraw.create(amount, code, walletId);
    this.cardLessWithdrawRepository.save(cardLessWithdraw);
  }
}
