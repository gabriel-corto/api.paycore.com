package com.labs.paycore.user.application;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labs.paycore.shared.domain.errors.NotFoundUserException;
import com.labs.paycore.user.domain.UserRepository;

@Service
public class GetProfileUseCase {
  private final UserRepository userRepository;

  public GetProfileUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public GetProfileUseCaseOutput execute(UUID userId) {
    var user = this.userRepository.findById(userId);

    if(user.isEmpty()) {
      throw new NotFoundUserException();
    }

    var output = new GetProfileUseCaseOutput(
      user.get().getId().toString(), 
      user.get().getName(), 
      user.get().getEmail().getValue(), 
      user.get().getNif().getValue()
    );

    return output;
  }
}
