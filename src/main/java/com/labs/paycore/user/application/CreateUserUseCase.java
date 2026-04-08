package com.labs.paycore.user.application;

import org.springframework.stereotype.Service;

import com.labs.paycore.user.domain.Email;
import com.labs.paycore.user.domain.Nif;
import com.labs.paycore.user.domain.User;
import com.labs.paycore.wallet.domain.Wallet;

import com.labs.paycore.user.domain.UserRepository;
import com.labs.paycore.user.domain.UserWithSameEmailException;
import com.labs.paycore.user.domain.UserWithSameNifException;
import com.labs.paycore.wallet.domain.WalletRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;
@Service
public class CreateUserUseCase {
  private final UserRepository userRepository;
  private final WalletRepository walletRepository;

  public CreateUserUseCase
  (
    UserRepository userRepository, 
    WalletRepository walletRepository
  ) 
  {
    this.userRepository = userRepository;
    this.walletRepository = walletRepository;
  }

  public void execute(CreateUserInput input) {
    var userWithSameEmail = this.userRepository.findByEmail(input.email());
    if(userWithSameEmail.isPresent()) {
      throw new UserWithSameEmailException();
    }

    var userWithSameNif = this.userRepository.findByNif(input.nif());
    if(userWithSameNif.isPresent()) {
      throw new UserWithSameNifException();
    }

    var passwordHashed = BCrypt.withDefaults().hashToString(12, input.password().toCharArray());

    var user = User.create(
      input.name(), 
      Email.create(input.email()), 
      Nif.create(input.nif()), 
      passwordHashed
    );

    var wallet = Wallet.create(user.getId());

    this.userRepository.save(user);
    this.walletRepository.save(wallet);
  }
}
