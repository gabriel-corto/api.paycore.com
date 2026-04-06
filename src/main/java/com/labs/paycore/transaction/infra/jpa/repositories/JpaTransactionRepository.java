package com.labs.paycore.transaction.infra.jpa.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.labs.paycore.transaction.infra.jpa.models.TransactionModel;

public interface JpaTransactionRepository extends JpaRepository<TransactionModel, UUID> {
  List<TransactionModel> findAllByWalletId(UUID walletId);
}