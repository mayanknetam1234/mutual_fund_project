package com.mayank.mutualFund.authentication.service;

import com.mayank.mutualFund.authentication.entity.*;

public interface HoldingTransactionService {


    HoldingTransaction createHoldingTransaction(User principalUser, MutualFund mutualFund, WalletTransaction walletTransaction, Holding savedHolding);

    HoldingTransaction saveHoldingTransaction(HoldingTransaction holdingTransactionToSave);
}
