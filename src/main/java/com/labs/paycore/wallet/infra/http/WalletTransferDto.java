package com.labs.paycore.wallet.infra.http;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record WalletTransferDto(
  @NotBlank(message = "Sender Wallet Id Não pode ser vazio!")
  @NotNull(message = "Sender Wallet Id Não pode ser vazio!")
  String senderWalletId,

  @NotBlank(message = "Amount Não pode ser vazio!")
  @NotNull(message = "Amount Wallet Id Não pode ser vazio!")
  String amount, 

  @NotBlank(message = "Repicient Não pode ser vazio!")
  @NotNull(message = "Repicient Não pode ser vazio!")
  String recipient
) {}
