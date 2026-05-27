package com.labs.paycore.auth.application;

import org.springframework.stereotype.Service;

import com.labs.paycore.auth.domain.InvalidCredentialsException;
import com.labs.paycore.shared.domain.errors.NotFoundUserException;
import com.labs.paycore.user.domain.UserRepository;
import com.labs.paycore.wallet.domain.NotFoundWalletException;
import com.labs.paycore.wallet.domain.WalletRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class LoginUseCase {
  private final UserRepository userRepository;
  private final WalletRepository walletRepository;

  public LoginUseCase(
    UserRepository userRepository, 
    WalletRepository walletRepository
  ) 
  {
    this.userRepository = userRepository;
    this.walletRepository = walletRepository;
  }

  public LoginUseCaseOutput execute(LoginUseCaseInput input) {
    var user = this.userRepository.findByEmail(input.email());

    if (user.isEmpty()) {
      throw new NotFoundUserException();
    }

    var password = BCrypt.verifyer().verify(input.password().toCharArray(),
        user.get().getPassword());

    if (!password.verified) {
      throw new InvalidCredentialsException();
    }

    var wallet = this.walletRepository.findByUserId(user.get().getId());

    if (wallet.isEmpty()) {
      throw new NotFoundWalletException();
    }

    return new LoginUseCaseOutput(
      wallet.get().getId().toString(), 
      user.get().getId().toString()
    );
  }
}
