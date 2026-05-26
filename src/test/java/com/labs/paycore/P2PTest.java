package com.labs.paycore;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.labs.paycore.transaction.infra.database.inmemory.InMemoryTransactionRepository;
import com.labs.paycore.user.application.CreateUserInput;
import com.labs.paycore.user.application.CreateUserUseCase;

import com.labs.paycore.user.infra.database.inmemory.InMemoryUserRepository;
import com.labs.paycore.wallet.application.WalletDepositUseCase;
import com.labs.paycore.wallet.application.WalletDepositUseCaseInput;
import com.labs.paycore.wallet.application.WalletP2PUseCase;
import com.labs.paycore.wallet.application.WalletP2PUseCaseInput;
import com.labs.paycore.wallet.infra.database.inmemory.InMemoryWalletRepository;

public class P2PTest {
  @Test
  @DisplayName("Should be able to transfer money from wallet to other wallet")
  void shouldBeAbleTransferMoneyToOtherWallet() {
    var userRepository = new InMemoryUserRepository();
    var walletRepository = new InMemoryWalletRepository();
    var transactionRepository = new InMemoryTransactionRepository();

    var createUserUseCase = new CreateUserUseCase(userRepository, walletRepository);
    var walletDepositUseCase = new WalletDepositUseCase(walletRepository, transactionRepository);
    var walletTransferUseCase = new WalletP2PUseCase(userRepository, walletRepository, transactionRepository);

    var createUserInput1 = new CreateUserInput("Fake User", "sender@email.com", "000000000AA000", "password");
    createUserUseCase.execute(createUserInput1);
    var sender = userRepository.findByEmail("sender@email.com");

    var walletDepositInput = new WalletDepositUseCaseInput(new BigDecimal(500), sender.get().getId());
    walletDepositUseCase.execute(walletDepositInput);
    var senderWallet = walletRepository.findByUserId(sender.get().getId());

    var createUserInput2 = new CreateUserInput("Fake User", "recipient@email.com", "000000000BB000", "password");
    createUserUseCase.execute(createUserInput2);
    var recipient = userRepository.findByEmail("recipient@email.com");

    var walletTransferInput = new WalletP2PUseCaseInput(
        senderWallet.get().getId(),
        new BigDecimal(500),
        "recipient@email.com");
    walletTransferUseCase.execute(walletTransferInput);
    var recipientWallet = walletRepository.findByUserId(recipient.get().getId());

    assertThat(recipientWallet.get().getBalance().toUnit()).isEqualTo(500);
    assertThat(senderWallet.get().getBalance().getValue()).isEqualTo(0);
  }
}
