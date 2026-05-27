package com.labs.paycore.user.infra.http;

public record GetMeResponse(
  String id, 
  String name, 
  String email, 
  String nif
) {}
