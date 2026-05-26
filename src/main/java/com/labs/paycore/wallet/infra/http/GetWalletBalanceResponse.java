package com.labs.paycore.wallet.infra.http;

import java.math.BigDecimal;

public record GetWalletBalanceResponse(
  BigDecimal balance
) {}
