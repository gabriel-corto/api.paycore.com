package com.labs.paycore.wallet.application;

import org.springframework.stereotype.Service;

import com.labs.paycore.transaction.domain.Transaction;
import com.labs.paycore.transaction.domain.TransactionOperation;
import com.labs.paycore.transaction.domain.TransactionRepository;
import com.labs.paycore.transaction.domain.TransactionType;

import com.labs.paycore.wallet.domain.Money;
import com.labs.paycore.wallet.domain.NotFoundWalletException;
import com.labs.paycore.wallet.domain.WalletRepository;

@Service
public class WalletDepositUseCase {

    private WalletRepository walletRepository;
    private TransactionRepository transactionRepository;

    public WalletDepositUseCase(
        WalletRepository walletRepository,
        TransactionRepository transactionRepository
    ) 
    {
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public void execute(WalletDepositUseCaseInput input) {
        var wallet = this.walletRepository.findByUserId(input.userId());

        if (wallet.isEmpty()) {
            throw new NotFoundWalletException();
        }

        long amount = input.amount().longValue();

        wallet.get().deposit(amount);

        var transaction = Transaction.create(
            Money.toCents(amount),
            TransactionOperation.DEPOSIT,
            TransactionType.INCOME,
            wallet.get().getId()
        );

        this.walletRepository.save(wallet.get());
        this.transactionRepository.save(transaction);
    }
}
