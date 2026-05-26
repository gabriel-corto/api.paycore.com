package com.labs.paycore.user.infra.http;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.shared.types.ApiResponse;
import com.labs.paycore.user.application.CreateUserUseCase;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
  private final CreateUserUseCase createUserUseCase;

  public UserController(CreateUserUseCase createUserUseCase) {
    this.createUserUseCase = createUserUseCase;
  }

  @PostMapping("/create")
  public ApiResponse<CreateUserResponse> createUser(@Valid @RequestBody CreateUserRequest body) {
    var input = CreateUserInputMapper.toCreateUserInput(body);

    var output = this.createUserUseCase.execute(input);

    return ApiResponse.success(new CreateUserResponse(
      output.id(),
      output.name(),
      output.email(),
      output.nif()
    ));
  }
}
