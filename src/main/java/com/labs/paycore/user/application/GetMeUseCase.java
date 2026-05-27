package com.labs.paycore.user.application;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.labs.paycore.shared.domain.errors.NotFoundUserException;
import com.labs.paycore.user.domain.UserRepository;

@Service
public class GetMeUseCase {
  private final UserRepository userRepository;

  public GetMeUseCase(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public GetMeUseCaseOutPut execute(UUID userId) {
    var user = this.userRepository.findById(userId);

    if(user.isEmpty()) {
      throw new NotFoundUserException();
    }

    var output = new GetMeUseCaseOutPut(
      user.get().getId().toString(), 
      user.get().getName(), 
      user.get().getEmail().getValue(), 
      user.get().getNif().getValue()
    );

    return output;
  }
}
