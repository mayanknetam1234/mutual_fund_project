package com.mayank.mutualFund.authentication.service;


import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;

/**
 * NOTE: Combines both HoldingTransactionService and WalletTransactionService
 */
public interface TransactionService {
    WalletTransaction manageBalanceOfUser(WalletTransactionDto walletTransactionDto);
}
