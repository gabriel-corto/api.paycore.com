package com.labs.paycore.transaction.infra.http;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.shared.types.ApiResponse;
import com.labs.paycore.transaction.application.GetAllTransactionsUseCase;
import com.labs.paycore.transaction.application.GetTransactionsByWalletIdUseCase;
import com.labs.paycore.transaction.application.TransactionOutput;

import jakarta.validation.Valid;

@Valid
@RestController
@RequestMapping("/transactions")
public class TransactionController {
  private GetTransactionsByWalletIdUseCase getTransactionsUseCase;
  private GetAllTransactionsUseCase getAllTransactionsUseCase;

  public TransactionController(
    GetTransactionsByWalletIdUseCase getTransactionsUseCase,
    GetAllTransactionsUseCase getAllTransactionsUseCase
  )
  {
    this.getTransactionsUseCase = getTransactionsUseCase;
    this.getAllTransactionsUseCase = getAllTransactionsUseCase;
  }

  @GetMapping("/get-by-wallet")
  public ApiResponse<List<TransactionOutput>> getTransactions(
    @RequestHeader("x-wallet-id")
    UUID walletId
  ) 
  {
    var transactions = this.getTransactionsUseCase.execute(walletId);
    return ApiResponse.success(transactions);
  }

  @GetMapping("")
  public ApiResponse<List<TransactionOutput>> getAllTransactions() {
    var transactions = this.getAllTransactionsUseCase.execute();
    return ApiResponse.success(transactions);
  }
}
