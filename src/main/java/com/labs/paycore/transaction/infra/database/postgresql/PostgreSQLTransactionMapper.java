package com.labs.paycore.transaction.infra.database.postgresql;

import com.labs.paycore.transaction.domain.Transaction;
import com.labs.paycore.transaction.infra.jpa.models.TransactionModel;
import com.labs.paycore.wallet.domain.Money;

public class PostgreSQLTransactionMapper {

    static Transaction toDomain(TransactionModel transactionModel) {
        return Transaction.restore(
            transactionModel.getId(),
            Money.fromCents(transactionModel.getAmount()),
            transactionModel.getOperation(),
            transactionModel.getType(),
            transactionModel.getStatus(),
            transactionModel.getCreatedAt(),
            transactionModel.getWalletId()
        );
    }

    static TransactionModel toJpaModel(Transaction transaction) {
        var transactionModel = new TransactionModel();

        transactionModel.setId(transaction.getId());
        transactionModel.setAmount(transaction.getAmount().getValue());
        transactionModel.setOperation(transaction.getOperation());
        transactionModel.setType(transaction.getType());
        transactionModel.setStatus(transaction.getStatus());
        transactionModel.setCreatedAt(transaction.getCreatedAt());
        transactionModel.setWalletId(transaction.getWalletId());

        return transactionModel;
    }
}
