package com.labs.paycore;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import com.labs.paycore.user.application.CreateUserInput;
import com.labs.paycore.user.application.CreateUserUseCase;
import com.labs.paycore.user.infra.database.inmemory.InMemoryUserRepository;

import com.labs.paycore.transaction.infra.database.inmemory.InMemoryTransactionRepository;

import com.labs.paycore.wallet.application.WalletDepositUseCase;
import com.labs.paycore.wallet.application.WalletDepositUseCaseInput;
import com.labs.paycore.wallet.infra.database.inmemory.InMemoryWalletRepository;

public class WalletDepositTest {
  @Test
  @DisplayName("Should be able to deposit in to an wallet")
  void shouldBeAbleToDepositInToAnWallet() {
    var transactionRepository = new InMemoryTransactionRepository();
    var walletRepository = new InMemoryWalletRepository();
    var userRepository = new InMemoryUserRepository();

    var createUserUseCase = new CreateUserUseCase(userRepository, walletRepository);
    var createUserUseCaseInput = new CreateUserInput(
        "Fake User",
        "fake@email.com",
        "000000000AA000",
        "Fake Password");
    createUserUseCase.execute(createUserUseCaseInput);

    var user = userRepository.findByEmail("fake@email.com");

    var walletDepositUseCase = new WalletDepositUseCase(walletRepository, transactionRepository);
    var WalletDepositUseCaseInput = new WalletDepositUseCaseInput(new BigDecimal(500), user.get().getId());
    walletDepositUseCase.execute(WalletDepositUseCaseInput);

    var wallet = walletRepository.findByUserId(user.get().getId());

    assertThat(wallet.get().getBalance().toUnit()).isEqualTo(500);
  }
}
