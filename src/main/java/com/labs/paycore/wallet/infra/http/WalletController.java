package com.labs.paycore.wallet.infra.http;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.shared.types.ApiResponse;
import com.labs.paycore.wallet.application.GetWalletBalanceUseCase;
import com.labs.paycore.wallet.application.WalletDepositUseCase;
import com.labs.paycore.wallet.application.WalletP2PUseCase;

import jakarta.validation.Valid;

import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Valid
@RestController
@RequestMapping("/wallet")
public class WalletController {
  private WalletDepositUseCase walletDepositUseCase;
  private WalletP2PUseCase walletTransferUseCase;
  private GetWalletBalanceUseCase getWalletBalanceUseCase;

  public WalletController(
    WalletDepositUseCase walletDepositUseCase,
    WalletP2PUseCase walletTransferUseCase,
    GetWalletBalanceUseCase getWalletBalanceUseCase
  ) 
  {
    this.walletDepositUseCase = walletDepositUseCase;
    this.walletTransferUseCase = walletTransferUseCase;
    this.getWalletBalanceUseCase = getWalletBalanceUseCase;
  }

  @PostMapping("/deposit")
  public ApiResponse<Void> deposit(
    @Valid @RequestBody WalletDepositRequest body,
    @RequestHeader("x-user-id") UUID userId
  ) 
  {
    var input = WalletInputMapper.toWalletDepositUseCaseInput(body, userId);
    this.walletDepositUseCase.execute(input);

    return ApiResponse.ok("Deposit completed successfully");
  }

  @PostMapping("/p2p")
  public ApiResponse<Void> p2p(
    @Valid @RequestBody WalletP2PRequest body,
    @RequestHeader("x-wallet-id") UUID walletId
  ) 
  {
    var input = WalletInputMapper.toWalletP2PUseCaseInput(body, walletId);
    this.walletTransferUseCase.execute(input);

    return ApiResponse.ok("P2P Transfer completed successfully");
  }

  @GetMapping("/balance")
  public ApiResponse<GetWalletBalanceResponse> getBalance(
    @RequestHeader("x-wallet-id") String walletId 
  ) 
  {
    var output = this.getWalletBalanceUseCase.execute(walletId);
    var response = new GetWalletBalanceResponse(output.balance());
    return ApiResponse.success(response);
  }
}
