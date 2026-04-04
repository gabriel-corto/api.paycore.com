package com.labs.paycore.wallet.infra.database.posgtresql;

import com.labs.paycore.wallet.domain.Wallet;
import com.labs.paycore.wallet.infra.jpa.models.WalletModel;

public class PostgresSQLWalletMapper {
  static WalletModel toJpaModel(Wallet wallet) {
    var walletModel = new WalletModel();

    walletModel.setId(wallet.getId());
    walletModel.setBalance(wallet.getBalance().getValue());
    walletModel.setUserId(wallet.getUserId());

    return walletModel;
  }
}
