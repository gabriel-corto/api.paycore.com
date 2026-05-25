package com.labs.paycore.wallet.domain;

import java.util.Optional;

public interface CardLessWithdrawRepository {
  void save(CardLessWithdraw withdraw);

  Optional<CardLessWithdraw> findByReference(Long reference);
}
