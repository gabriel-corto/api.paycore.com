package com.labs.paycore.auth.infra.http;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record LoginDto(
  @NotBlank(message = "Email is required")
  @NotNull(message = "Email is required")
  @NotEmpty(message = "Email is required")
  @Email(message = "Email is invalid")
  String email,

  @NotBlank(message = "Password is required")
  @NotNull(message = "Password is required")
  @NotEmpty(message = "Password is required")
  String password
  ) 
{
}
