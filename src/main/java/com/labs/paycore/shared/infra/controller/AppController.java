package com.labs.paycore.shared.infra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

  @GetMapping("/")
  public ResponseEntity<WelcomeResponse> hello() {
    return ResponseEntity.ok(new WelcomeResponse("🚀 Welcome to api.paycore.com"));
  }
}
