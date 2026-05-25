package com.labs.paycore.auth.infra.http;

public class LoginResponse {
  private String token;

  private LoginResponse(String token) {
    this.token = token;
  }

  public static LoginResponse create(String token) {
    return new LoginResponse(token);
  }

  public String getToken() {
    return token;
  }
}
