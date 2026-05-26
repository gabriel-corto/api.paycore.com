package com.labs.paycore.user.infra.http;

public record CreateUserResponse(
  String id,
  String name,
  String email,
  String nif
) {}
