package com.labs.paycore.wallet.application;

public record CardLessWidthdrawUseCaseInput(
                String amount,
                Integer code,
                String walletId) {

}
