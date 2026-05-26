package com.labs.paycore.user.application;

public record CreateUserUseCaseOutput(
  String id,
  String name,
  String email,
  String nif
) {}
