package com.labs.paycore.transaction.infra.jpa.models;

import com.labs.paycore.transaction.domain.TransactionOperation;
import com.labs.paycore.transaction.domain.TransactionStatus;
import com.labs.paycore.transaction.domain.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "transactions")
@Getter
@Setter
public class TransactionModel {

    @Id
    private UUID id;

    @Column(name = "amount")
    private long amount;

    @Column(name = "operation")
    @Enumerated(EnumType.STRING)
    private TransactionOperation operation;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "wallet_id")
    private UUID walletId;
}
