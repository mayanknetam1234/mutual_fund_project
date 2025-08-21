package com.mayank.mutualFund.authentication.service;

import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;

public interface WalletTransactionService {
    WalletTransaction saveTransaction(WalletTransactionDto walletTransactionDto);
}
