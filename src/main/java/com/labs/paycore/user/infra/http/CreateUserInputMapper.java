package com.labs.paycore.user.infra.http;

import com.labs.paycore.user.application.CreateUserInput;

public record CreateUserInputMapper() {
  public static CreateUserInput toCreateUserInput(CreateUserRequest request) {
    return new CreateUserInput(
      request.name(),
      request.email(),
      request.nif(),
      request.password()
    );
  }
}
