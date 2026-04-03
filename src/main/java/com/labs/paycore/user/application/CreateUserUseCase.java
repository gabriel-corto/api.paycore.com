package com.labs.paycore.user.application;

import org.springframework.stereotype.Service;

import com.labs.paycore.user.domain.UserRepository;

@Service
public class CreateUserUseCase {
  private final UserRepository userRepository;

  public CreateUserUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  void execute(CreateUserInput input) {
    var userWithSameEmail = this.userRepository.findByEmail(input.email());

    if(userWithSameEmail.isPresent()) {
      //
    }

    var userWithSameNif = this.userRepository.findByNif(input.nif());

    if(userWithSameNif.isPresent()) {
      //
    }
  }
}
