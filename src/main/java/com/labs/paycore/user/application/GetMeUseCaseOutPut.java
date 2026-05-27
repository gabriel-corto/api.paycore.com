package com.labs.paycore.user.application;

public record GetMeUseCaseOutPut(
  String id, 
  String name, 
  String email, 
  String nif
) {}
