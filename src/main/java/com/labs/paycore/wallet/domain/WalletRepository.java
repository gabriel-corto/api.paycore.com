package com.labs.paycore.wallet.domain;

import java.util.Optional;
import java.util.UUID;

public interface WalletRepository {
  void save(Wallet wallet);
  Optional<Wallet> findByUserId(UUID userId);
} 