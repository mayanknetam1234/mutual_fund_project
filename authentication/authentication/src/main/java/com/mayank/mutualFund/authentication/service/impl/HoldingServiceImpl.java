package com.mayank.mutualFund.authentication.service.impl;

import com.mayank.mutualFund.authentication.dto.InvestRequestDto;
import com.mayank.mutualFund.authentication.entity.Holding;
import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import com.mayank.mutualFund.authentication.enumClasses.PaymentType;
import com.mayank.mutualFund.authentication.repository.HoldingRepository;
import com.mayank.mutualFund.authentication.service.HoldingService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HoldingServiceImpl implements HoldingService {

    private final HoldingRepository holdingRepository;

    public HoldingServiceImpl(HoldingRepository holdingRepository) {
        this.holdingRepository = holdingRepository;
    }

    @Override
    public Optional<Holding> getHoldingByUserAndMutualFund(User user, MutualFund mutualFund) {
        return holdingRepository.findByUserAndMutualFund(user,mutualFund);
    }

    @Override
    public Holding createHolding(User principalUser, MutualFund mutualFund, InvestRequestDto investRequestDto) {

        return Holding.builder()
                .currentInvestment(investRequestDto.getAmount())
                .unitsAllocated(investRequestDto.getAmount()/mutualFund.getNav().getNav())
                .user(principalUser)
                .mutualFund(mutualFund)
                .build();
    }

    @Override
    public Holding saveHolding(Holding holdingToSave) {
        return holdingRepository.save(holdingToSave);
    }

    @Override
    public Holding changeHoldingInvestment(Holding holding, WalletTransaction walletTransaction, MutualFund mutualFund) {
        if(walletTransaction.getPaymentType()== PaymentType.INVEST){
            holding.setCurrentInvestment(
                    holding.getCurrentInvestment()+ walletTransaction.getAmount()
            );
            holding.setUnitsAllocated(holding.getUnitsAllocated()+ walletTransaction.getAmount()/mutualFund.getNav().getNav());
        }
        //TODO:implement the fund withdraw case
//        else if(walletTransaction.getPaymentType()==PaymentType.FUND_WITHDRAW){
//
//        }
        return holding;
    }

    @Override
    public Iterable<Holding> getAllHoldingOfUser(User principleUser) {
        return holdingRepository.findByUser(principleUser);
    }

    @Override
    public Optional<Holding> getHoldingByHoldingId(Long holdingId) {
        return holdingRepository.findById(holdingId);
    }
}
