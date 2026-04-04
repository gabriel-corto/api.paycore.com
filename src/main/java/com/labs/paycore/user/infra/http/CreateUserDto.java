package com.labs.paycore.user.infra.http;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateUserDto(
  @NotNull(message = "Nome é obrigatório")
  @NotBlank(message = "Nome é obrigatório")
  String name,

  @NotNull(message = "Email é obrigatório")
  @NotBlank(message = "Email é obrigatório")
  @Email
  String email,

  @NotNull(message = "NIF é obrigatório")
  @NotBlank(message = "NIF é obrigatório")
  String nif,
  
  @NotNull(message = "Password é obrigatória")
  @NotBlank(message = "Password é obrigatória")
  @Min(value = 6, message = "Password deve ter pelo menos 6 caracteres")
  String password
) {
  
}
