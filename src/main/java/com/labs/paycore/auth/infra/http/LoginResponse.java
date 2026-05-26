package com.labs.paycore.auth.infra.http;

public record LoginResponse(String token) {
  public static LoginResponse wrap(String token) {
    return new LoginResponse(token);
  }
}
