package com.labs.paycore.auth.infra.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.auth.application.LoginUseCase;
import com.labs.paycore.auth.application.LoginUseCaseInput;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/auth")
public class AuthController {
  private LoginUseCase loginUseCase;

  public AuthController(LoginUseCase loginUseCase) {
    this.loginUseCase = loginUseCase;
  }

  @PostMapping("/login")  
  public LoginResponse login(@Valid @RequestBody LoginDto body) {
    var output = this.loginUseCase.execute(new LoginUseCaseInput(
      body.email(), 
      body.password()
    ));
    return LoginResponse.create(output.accessToken());
  }

}
