package com.labs.paycore.wallet.infra.database.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.labs.paycore.wallet.domain.Wallet;
import com.labs.paycore.wallet.domain.WalletRepository;

@Repository
public class InMemoryWalletRepository implements WalletRepository {
  private List<Wallet> wallet = new ArrayList<>();

  @Override
  public void save(Wallet wallet) {
    this.wallet.add(wallet);
  }

  @Override
  public Optional<Wallet> findById(UUID id) {
    return this.wallet.stream().filter(w -> w.getId() == id).findFirst();
  }

  @Override
  public Optional<Wallet> findByUserId(UUID userId) {
    return this.wallet.stream().filter(w -> w.getUserId() == userId).findFirst();
  }
  
}
