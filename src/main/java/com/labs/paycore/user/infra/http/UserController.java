package com.labs.paycore.user.infra.http;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.shared.types.ApiResponse;
import com.labs.paycore.user.application.CreateUserUseCase;
import com.labs.paycore.user.application.GetProfileUseCase;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;

@Valid
@RestController
@RequestMapping("/users")
public class UserController {
  private final CreateUserUseCase createUserUseCase;
  private final GetProfileUseCase getProfileUseCase;

  public UserController(
    GetProfileUseCase getProfileUseCase,
    CreateUserUseCase createUserUseCase
  ) 
  {
    this.getProfileUseCase = getProfileUseCase;
    this.createUserUseCase = createUserUseCase;
  }

  @GetMapping("/profile")
  public ApiResponse<GetProfileResponse> getProfile(
    @RequestHeader("x-user-id") UUID userId
  ) 
  {
    var output = this.getProfileUseCase.execute(userId);
    
    return ApiResponse.success(new GetProfileResponse(
      output.id(), 
      output.name(), 
      output.email(),
      output.nif()
    ));
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
