package com.labs.paycore.auth.infra.http;

import com.labs.paycore.auth.application.LoginUseCaseInput;

public class AuthInputMapper {
  static LoginUseCaseInput toLoginInput(LoginRequest body) {
    return new LoginUseCaseInput(body.email(), body.password());
  }
}
