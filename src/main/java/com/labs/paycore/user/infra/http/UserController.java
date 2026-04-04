package com.labs.paycore.user.infra.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.user.application.CreateUserInput;
import com.labs.paycore.user.application.CreateUserUseCase;

@RestController
@RequestMapping("/users")
public class UserController {
  private final CreateUserUseCase createUserUseCase;

  public UserController(CreateUserUseCase createUserUseCase) {
    this.createUserUseCase = createUserUseCase;
  }

  @PostMapping("/create")
  public ResponseEntity<Void> createUser(@RequestBody CreateUserDto body) {
    var input = new CreateUserInput(body.name(), body.email(), body.nif(), body.password());
    this.createUserUseCase.execute(input);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
