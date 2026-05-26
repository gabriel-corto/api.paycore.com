package com.labs.paycore.auth.infra.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.auth.application.LoginUseCase;
import com.labs.paycore.auth.application.LoginUseCaseInput;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {
  private LoginUseCase loginUseCase;

  public AuthController(LoginUseCase loginUseCase) {
    this.loginUseCase = loginUseCase;
  }

  @PostMapping("/login")  
  public LoginResponse login(@Valid @RequestBody LoginDto body) {
    var input = new LoginUseCaseInput(body.email(), body.password());

    var output = this.loginUseCase.execute(input);

    return LoginResponse.wrap(output.accessToken());
  }

}
