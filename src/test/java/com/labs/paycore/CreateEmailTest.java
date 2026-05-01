package com.labs.paycore;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.labs.paycore.user.domain.Email;
import com.labs.paycore.user.domain.InvalidEmailException;

public class CreateEmailTest {
  @Test
  @DisplayName("Should create a valid email")
  void shouldCreateAValidEmail() {
    var email = Email.create("user@email.com");
    assertThat(email.getValue()).isEqualTo("user@email.com");
  }

  @Test
  @DisplayName("Should throw an exception when try to create a invalid email")
  void shouldThrowAnExceptionWhenTryToCreateAInvalidEmail() {
    assertThrows(InvalidEmailException.class, () -> {
      Email.create("invalid-email");
    });
  }
}
