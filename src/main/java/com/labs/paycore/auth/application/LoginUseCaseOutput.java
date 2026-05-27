package com.labs.paycore.auth.application;

public record LoginUseCaseOutput(
    String walletId,
    String userId
) {}
