package com.labs.paycore.auth.infra.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.auth.application.LoginUseCase;
import com.labs.paycore.shared.types.ApiResponse;

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
  public ApiResponse<LoginResponse> login(@Valid @RequestBody LoginRequest body) {
    var input = AuthInputMapper.toLoginInput(body);

    var output = this.loginUseCase.execute(input);

    return ApiResponse.success(new LoginResponse(
      output.walletId(), 
      output.userId()
    ));
  }

}
