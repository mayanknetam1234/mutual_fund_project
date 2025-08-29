package com.mayank.mutualFund.authentication.service;

import com.mayank.mutualFund.authentication.dto.InvestRequestDto;
import com.mayank.mutualFund.authentication.entity.Holding;
import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;

import java.util.Optional;

public interface HoldingService {
    Optional<Holding> getHoldingByUserAndMutualFund(User user, MutualFund mutualFund);

    Holding createHolding(User principalUser, MutualFund mutualFund, InvestRequestDto investRequestDto);

    Holding saveHolding(Holding holdingToSave);


    Holding changeHoldingInvestment(Holding holding, WalletTransaction walletTransaction, MutualFund mutualFund);
}
