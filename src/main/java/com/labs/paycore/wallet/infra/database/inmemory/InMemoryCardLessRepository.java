package com.labs.paycore.wallet.infra.database.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.labs.paycore.wallet.domain.CardLessWithdraw;
import com.labs.paycore.wallet.domain.CardLessWithdrawRepository;

@Repository
public class InMemoryCardLessRepository implements CardLessWithdrawRepository {
  private List<CardLessWithdraw> withdrawers = new ArrayList<>();

  @Override
  public void save(CardLessWithdraw withdraw) {
    this.withdrawers.add(withdraw);
  }

  @Override
  public Optional<CardLessWithdraw> findByReference(Long reference) {
    return this.withdrawers.stream().filter(w -> w.getReference().equals(reference)).findFirst();
  }
}
