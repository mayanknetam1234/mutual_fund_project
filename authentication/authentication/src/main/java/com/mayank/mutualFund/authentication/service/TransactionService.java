package com.mayank.mutualFund.authentication.service;


import com.mayank.mutualFund.authentication.dto.InvestRequestDto;
import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.Holding;
import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;

/**
 * NOTE: Combines both HoldingTransactionService and WalletTransactionService
 */
public interface TransactionService {
    WalletTransaction manageBalanceOfUser(WalletTransactionDto walletTransactionDto);

    Holding investInHoldingAndManageTransaction(User principalUser, MutualFund mutualFund, InvestRequestDto investRequestDto);
}
