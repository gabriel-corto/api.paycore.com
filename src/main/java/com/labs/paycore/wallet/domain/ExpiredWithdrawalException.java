package com.labs.paycore.wallet.domain;

import com.labs.paycore.shared.domain.errors.DomainError;

public class ExpiredWithdrawalException extends DomainError {
    public ExpiredWithdrawalException() {
        super("Este levantamento já expirou!");
    }
}
