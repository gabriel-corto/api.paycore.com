package com.labs.paycore.shared.types;

import lombok.Getter;

@Getter
public class ApiResponse<T> {
  private final T data;
  private final String message;

  public ApiResponse(T data, String message) {
    this.data = data;
    this.message = message;
  }

  public static <T> ApiResponse<T> success(T data) {
    return new ApiResponse<>(data, null);
  }

  public static <T> ApiResponse<T> ok(String message) {
    return new ApiResponse<>(null, message);
  }
}
