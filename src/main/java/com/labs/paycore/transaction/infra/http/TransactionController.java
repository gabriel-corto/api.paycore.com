package com.labs.paycore.transaction.infra.http;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.labs.paycore.transaction.application.GetAllTransactionsUseCase;
import com.labs.paycore.transaction.application.GetTransactionsByWalletIdUseCase;
import com.labs.paycore.transaction.application.TransactionOutput;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
  private GetTransactionsByWalletIdUseCase getTransactionsUseCase;
  private GetAllTransactionsUseCase getAllTransactionsUseCase;

  public TransactionController
  (
    GetTransactionsByWalletIdUseCase getTransactionsUseCase,
    GetAllTransactionsUseCase getAllTransactionsUseCase
  )
  {
    this.getTransactionsUseCase = getTransactionsUseCase;
    this.getAllTransactionsUseCase = getAllTransactionsUseCase;
  }

  @GetMapping("/{walletId}")
  public ResponseEntity<List<TransactionOutput>> getTransactions(@PathVariable String walletId) {
    var transactions = this.getTransactionsUseCase.execute(walletId);
    return ResponseEntity.ok(transactions);
  }

  @GetMapping("")
  public ResponseEntity<List<TransactionOutput>> getAllTransactions() {
    var transactions = this.getAllTransactionsUseCase.execute();
    return ResponseEntity.ok(transactions);
  }
}
