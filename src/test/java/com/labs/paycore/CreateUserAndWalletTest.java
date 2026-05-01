package com.labs.paycore;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import com.labs.paycore.user.application.CreateUserInput;
import com.labs.paycore.user.application.CreateUserUseCase;
import com.labs.paycore.user.domain.UserWithSameEmailException;
import com.labs.paycore.user.domain.UserWithSameNifException;
import com.labs.paycore.user.infra.database.inmemory.InMemoryUserRepository;
import com.labs.paycore.wallet.infra.database.inmemory.InMemoryWalletRepository;

public class CreateUserAndWalletTest {
  @Test
  @DisplayName("Should create a user account and create a wallet for this user with balance 0")
  void shouldCreateUserAndCreateWallet() {
    var userRepository = new InMemoryUserRepository(); 
    var walletRepository = new InMemoryWalletRepository();

    var createUserUseCase = new CreateUserUseCase(userRepository, walletRepository);
    var createUserInput = new CreateUserInput("Fake User", "fake@user.com", "000000000AA000", "123456");
    
    createUserUseCase.execute(createUserInput); 

    var user = userRepository.findByEmail("fake@user.com");
    var wallet = walletRepository.findByUserId(user.get().getId());

    assertThat(user.isPresent()).isTrue();
    assertThat(wallet.isPresent()).isTrue();
    assertThat(wallet.get().getBalance().getValue()).isEqualTo(0);
  }

  @Test
  @DisplayName("Should not create a user account with nif already registered")
  void shouldNotCreateAUserAccountWithNifAlreadyRegistered() {
    var userRepository = new InMemoryUserRepository(); 
    var walletRepository = new InMemoryWalletRepository();
    var createUserUseCase = new CreateUserUseCase(userRepository, walletRepository);

    var firstInput = new CreateUserInput("User 1", "user1@email.com", "000000000AA000", "123456");
    var secondInput = new CreateUserInput("User 2", "user2@email.com", "000000000AA000", "654321");

    createUserUseCase.execute(firstInput);

    assertThrows(UserWithSameNifException.class, () -> {
      createUserUseCase.execute(secondInput);
    });
    assertThat(userRepository.findByEmail("user2@email.com")).isEmpty();
  }

  @Test
  @DisplayName("Should not create a user account with email already registered")
  void shouldNotCreateAUserAccountWithEmailAlreadyRegistered() {
    var userRepository = new InMemoryUserRepository(); 
    var walletRepository = new InMemoryWalletRepository(); 
    var createUserUseCase = new CreateUserUseCase(userRepository, walletRepository);

    var firstInput = new CreateUserInput("User 1", "user1@email.com", "000000000AA000", "123456");
    var secondInput = new CreateUserInput("User 2", "user1@email.com", "000000000BB000", "654321");

    createUserUseCase.execute(firstInput);

    assertThrows(UserWithSameEmailException.class, () -> {
      createUserUseCase.execute(secondInput);
    });
    assertThat(userRepository.findByNif("000000000AA000")).isPresent();
    assertThat(userRepository.findByNif("000000000BB000")).isEmpty();
  }
}
