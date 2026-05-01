package com.labs.paycore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.labs.paycore.user.domain.InvalidNifException;
import com.labs.paycore.user.domain.Nif;

public class CreateNifTest {
  @Test
  @DisplayName("Should create a valid NIF")
  void shouldCreateAValidNif() {
    var nif = Nif.create("123456789AA000");
    assertThat(nif.getValue()).isEqualTo("123456789AA000");
  }

  @Test
  @DisplayName("Should throw an exception when try to create a invalid NIF")
  void shouldThrowAnExceptionWhenTryToCreateAInvalidNif() {
    assertThrows(InvalidNifException.class, () -> {
      Nif.create("invalid-nif");
    });
  }
}
