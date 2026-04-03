package com.labs.paycore.user.application;

public record CreateUserInput(
  String name, 
  String email, 
  String nif, 
  String password
) {
  
}
