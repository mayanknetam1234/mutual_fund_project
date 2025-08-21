package com.mayank.mutualFund.authentication.service;

public interface WalletValidationService {
    Boolean checkWithdrawAmount(String email,Double withdrawAmount);
    Boolean isPinCorrect(String email,Integer pin);
}
