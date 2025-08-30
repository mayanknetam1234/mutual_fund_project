package com.mayank.mutualFund.authentication.service;

import com.mayank.mutualFund.authentication.entity.*;

public interface HoldingTransactionService {


    HoldingTransaction createHoldingTransaction(User principalUser, MutualFund mutualFund, WalletTransaction walletTransaction, Holding savedHolding);

    HoldingTransaction saveHoldingTransaction(HoldingTransaction holdingTransactionToSave);

    Iterable<HoldingTransaction> getAllHoldingTransactionByUserAndHolding(User principleUser, Holding principleHolding);

    Iterable<HoldingTransaction> getAllHoldingTransactionByUser(User principleUser);
}
