package com.labs.paycore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.labs.paycore.wallet.domain.InvalidAmountException;
import com.labs.paycore.wallet.domain.Money;

public class MoneyTest {
  @Test
  @DisplayName("Should create a valid money with units value")
  void shouldCreateAValidMoneyWithUnitsValue() {
    var money = Money.toUnits(500);
    assertThat(money.getValue()).isEqualTo(500);
  }

  @Test
  @DisplayName("Should create a valid money with cents value")
  void shouldCreateAValidMoneyWithCentsValue() {
    var money = Money.toCents(500);
    assertThat(money.getValue()).isEqualTo(50000);
  }

  @Test
  @DisplayName("Should not be able to add negative value to money")
  void shouldNotBeAbleToAddNegativeValueToMoney() {
    assertThrows(InvalidAmountException.class, () -> {
      Money.toUnits(-100);
    });
  }

}
