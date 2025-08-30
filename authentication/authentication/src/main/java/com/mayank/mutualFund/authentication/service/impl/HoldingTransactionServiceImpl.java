package com.mayank.mutualFund.authentication.service.impl;

import com.mayank.mutualFund.authentication.entity.*;
import com.mayank.mutualFund.authentication.repository.HoldingRepository;
import com.mayank.mutualFund.authentication.repository.HoldingTransactionRepository;
import com.mayank.mutualFund.authentication.service.HoldingTransactionService;
import org.springframework.stereotype.Service;

@Service
public class HoldingTransactionServiceImpl implements HoldingTransactionService {
   private final HoldingTransactionRepository holdingTransactionRepository;

    public HoldingTransactionServiceImpl( HoldingTransactionRepository holdingTransactionRepository) {
        this.holdingTransactionRepository = holdingTransactionRepository;

    }

    @Override
    public HoldingTransaction createHoldingTransaction(User principalUser, MutualFund mutualFund, WalletTransaction walletTransaction, Holding savedHolding) {
        return HoldingTransaction.builder()
                .amount(walletTransaction.getAmount())
                .unitsAllocated(walletTransaction.getAmount()/mutualFund.getNav().getNav())
                .nav(mutualFund.getNav())
                .holding(savedHolding)
                .user(principalUser)
                .build();
    }

    @Override
    public HoldingTransaction saveHoldingTransaction(HoldingTransaction holdingTransactionToSave) {
        return holdingTransactionRepository.save(holdingTransactionToSave);
    }

    @Override
    public Iterable<HoldingTransaction> getAllHoldingTransactionByUserAndHolding(User principleUser, Holding principleHolding) {
        return holdingTransactionRepository.findByUserAndHolding(principleUser,principleHolding);
    }

    @Override
    public Iterable<HoldingTransaction> getAllHoldingTransactionByUser(User principleUser) {
        return holdingTransactionRepository.findByUser(principleUser);
    }
}
