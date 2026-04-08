package com.labs.paycore.wallet.infra.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.wallet.application.GetBalanceUseCaseOutput;
import com.labs.paycore.wallet.application.GetWalletBalanceUseCase;
import com.labs.paycore.wallet.application.WalletDepositUseCase;
import com.labs.paycore.wallet.application.WalletDepositUseCaseInput;
import com.labs.paycore.wallet.application.WalletTransferUseCase;
import com.labs.paycore.wallet.application.WalletTransferUseCaseInput;

import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/wallet")
public class WalletController {
  private WalletDepositUseCase walletDepositUseCase;
  private WalletTransferUseCase walletTransferUseCase;
  private GetWalletBalanceUseCase getWalletBalanceUseCase;

  public WalletController(
    WalletDepositUseCase walletDepositUseCase, 
    WalletTransferUseCase walletTransferUseCase,
    GetWalletBalanceUseCase getWalletBalanceUseCase
  ) {
    this.walletDepositUseCase = walletDepositUseCase;
    this.walletTransferUseCase = walletTransferUseCase;
    this.getWalletBalanceUseCase = getWalletBalanceUseCase;
  }

  @PostMapping("/deposit")
  public void deposit(@Valid @RequestBody WalletDepositDto body) {
    var input = new WalletDepositUseCaseInput(body.amount(), body.userId());
    this.walletDepositUseCase.execute(input);
  }

  @PostMapping("/transfer")
  public void transfer(@Valid @RequestBody WalletTransferDto body) {
    var input = new WalletTransferUseCaseInput(body.senderWalletId(), body.amount(), body.recipient());
    this.walletTransferUseCase.execute(input);
  }

  @GetMapping("/{userId}/balance")
  public ResponseEntity<GetBalanceUseCaseOutput> getBalance(@PathVariable String userId) {
    var output = this.getWalletBalanceUseCase.execute(userId);
    return ResponseEntity.ok(output);
  }
}
