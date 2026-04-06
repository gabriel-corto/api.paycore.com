package com.labs.paycore.wallet.infra.database.posgtresql;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.labs.paycore.wallet.domain.Wallet;
import com.labs.paycore.wallet.domain.WalletRepository;
import com.labs.paycore.wallet.infra.jpa.repositories.JpaWalletRepository;

@Repository
public class PostgreSQLWalletRepository implements WalletRepository {
  private final JpaWalletRepository jpaWalletRepository;

  public PostgreSQLWalletRepository(JpaWalletRepository jpaWalletRepository) {
    this.jpaWalletRepository = jpaWalletRepository;
  }

  @Override
  public void save(Wallet wallet) {
    this.jpaWalletRepository.save(PostgresSQLWalletMapper.toJpaModel(wallet));
  }

  @Override
  public Optional<Wallet> findByUserId(UUID userId) {
    return this.jpaWalletRepository.findFirstByUserId(userId)
      .map(w -> PostgresSQLWalletMapper.toDomain(w));
  }
}
