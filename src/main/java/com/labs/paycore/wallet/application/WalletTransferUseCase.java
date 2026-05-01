package com.labs.paycore.wallet.application;

import com.labs.paycore.transaction.domain.Transaction;
import com.labs.paycore.transaction.domain.TransactionOperation;
import com.labs.paycore.transaction.domain.TransactionRepository;
import com.labs.paycore.transaction.domain.TransactionType;
import com.labs.paycore.user.domain.NotFoundRecipientException;
import com.labs.paycore.user.domain.UserRepository;
import com.labs.paycore.wallet.domain.Money;
import com.labs.paycore.wallet.domain.NotFoundWalletException;
import com.labs.paycore.wallet.domain.SelfTransferException;
import com.labs.paycore.wallet.domain.WalletRepository;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class WalletTransferUseCase {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    public WalletTransferUseCase(
        UserRepository userRepository,
        WalletRepository walletRepository,
        TransactionRepository transactionRepository
    ) {
        this.userRepository = userRepository;
        this.walletRepository = walletRepository;
        this.transactionRepository = transactionRepository;
    }

    public void execute(WalletTransferUseCaseInput input) {
        UUID senderWalletId = UUID.fromString(input.senderWalletId());
        var senderWallet = this.walletRepository.findById(senderWalletId);

        if (senderWallet.isEmpty()) {
            throw new NotFoundWalletException();
        }

        var recipient = this.userRepository.findByEmail(input.recipient());
        if (recipient.isEmpty()) {
            throw new NotFoundRecipientException();
        }

        var isSelfTransfer = recipient
            .get()
            .getId()
            .equals(senderWallet.get().getUserId());
        if (isSelfTransfer) {
            throw new SelfTransferException();
        }

        var amountInDecimal = new BigDecimal(input.amount());
        var amount = amountInDecimal.longValue();

        senderWallet.get().withdraw(amount);

        var recipientWallet = this.walletRepository.findByUserId(
            recipient.get().getId()
        );
        recipientWallet.get().deposit(amount);

        walletRepository.save(senderWallet.get());
        walletRepository.save(recipientWallet.get());

        var senderTransaction = Transaction.create(
            Money.fromUnits(amount),
            TransactionOperation.TRANSFER,
            TransactionType.OUTCOME,
            senderWalletId
        );

        var recipientTransaction = Transaction.create(
            Money.fromUnits(amount),
            TransactionOperation.TRANSFER,
            TransactionType.INCOME,
            recipientWallet.get().getUserId()
        );

        transactionRepository.save(senderTransaction);
        transactionRepository.save(recipientTransaction);
    }
}
