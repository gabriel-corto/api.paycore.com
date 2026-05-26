package com.labs.paycore.auth.application;

import org.springframework.stereotype.Service;

import com.labs.paycore.auth.domain.InvalidCredentialsException;
import com.labs.paycore.shared.domain.errors.NotFoundUserException;
import com.labs.paycore.user.domain.UserRepository;

import at.favre.lib.crypto.bcrypt.BCrypt;

@Service
public class LoginUseCase {
  private UserRepository userRepository;

  public LoginUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
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

    return new LoginUseCaseOutput("fakeAccessToken");
  }
}
