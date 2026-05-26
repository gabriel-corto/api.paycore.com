package com.labs.paycore.user.application;

public record GetProfileUseCaseOutput(
  String id, 
  String name, 
  String email, 
  String nif
) {}
