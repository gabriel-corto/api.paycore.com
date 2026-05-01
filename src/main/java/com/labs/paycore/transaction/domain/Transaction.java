package com.labs.paycore.transaction.domain;

import com.labs.paycore.wallet.domain.Money;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {

    private UUID id;
    private Money amount;
    private TransactionOperation operation;
    private TransactionType type;
    private TransactionStatus status;
    private LocalDateTime createdAt;
    private UUID walletId;

    private Transaction(
        UUID id,
        Money amount,
        TransactionOperation operation,
        TransactionType type,
        TransactionStatus status,
        LocalDateTime createdAt,
        UUID walletId
    ) {
        this.id = id;
        this.amount = amount;
        this.operation = operation;
        this.type = type;
        this.status = status;
        this.createdAt = createdAt;
        this.walletId = walletId;
    }

    public static Transaction create(
        Money amount,
        TransactionOperation operation,
        TransactionType type,
        UUID walletId
    ) {
        return new Transaction(
            UUID.randomUUID(),
            amount,
            operation,
            type,
            TransactionStatus.PENDING,
            LocalDateTime.now(),
            walletId
        );
    }

    public static Transaction restore(
        UUID id,
        Money amount,
        TransactionOperation operation,
        TransactionType type,
        TransactionStatus status,
        LocalDateTime createdAt,
        UUID walletId
    ) {
        return new Transaction(
            id,
            amount,
            operation,
            type,
            status,
            createdAt,
            walletId
        );
    }
}
