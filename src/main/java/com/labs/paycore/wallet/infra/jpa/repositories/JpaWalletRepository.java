package com.labs.paycore.wallet.infra.jpa.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labs.paycore.wallet.infra.jpa.models.WalletModel;

public interface JpaWalletRepository extends JpaRepository<WalletModel, UUID> {
  Optional<WalletModel> findFirstByUserId(UUID userId);
}
