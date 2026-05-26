package com.labs.paycore.wallet.infra.http;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletDepositRequest(
  @NotNull(message = "Quantidade é obrigatório")
  @NotBlank(message = "Quantidade é obrigatório")
  String amount, 

  @NotNull(message = "user_id é obrigatório")
  @NotBlank(message = "user_id é obrigatório")
  String userId
) {
}