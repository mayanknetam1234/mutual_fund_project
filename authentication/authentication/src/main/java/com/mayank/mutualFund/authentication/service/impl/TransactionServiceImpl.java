package com.mayank.mutualFund.authentication.service.impl;

import com.mayank.mutualFund.authentication.dto.InvestRequestDto;
import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.*;
import com.mayank.mutualFund.authentication.enumClasses.PaymentMethod;
import com.mayank.mutualFund.authentication.enumClasses.PaymentType;
import com.mayank.mutualFund.authentication.service.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final WalletTransactionService walletTransactionService;
    private final UserService userService;
    private final HoldingTransactionService holdingTransactionService;
    private final HoldingService holdingService;

    public TransactionServiceImpl(WalletTransactionService walletTransactionService, UserService userService, HoldingTransactionService holdingTransactionService, HoldingService holdingService) {
        this.walletTransactionService = walletTransactionService;
        this.userService = userService;
        this.holdingTransactionService = holdingTransactionService;
        this.holdingService = holdingService;
    }

    @Override
    /*
    * Takes the walletTransactionDto(user input ) as input and according to that it changes the balance of the user also adds the
    * transaction details in walletTransaction Relation(table)
    * */
    @Transactional
    public WalletTransaction manageBalanceOfUser(WalletTransactionDto walletTransactionDto) {

        WalletTransaction walletTransaction=walletTransactionService.saveTransaction(walletTransactionDto);

        userService.updateWallet(walletTransaction);

        return walletTransaction;
    }

    @Override
    @Transactional
    public Holding investInHoldingAndManageTransaction(User principalUser, MutualFund mutualFund, InvestRequestDto investRequestDto) {

        //check if holding exists or not

        Optional<Holding> holdingOptional=holdingService.getHoldingByUserAndMutualFund(principalUser,mutualFund);

        //let's create transactions and holding to save
        //walletTransaction->user
        //TODO : delete wallet transaction and create APP_TRANSFER payment method
        WalletTransactionDto walletTransactionDto=WalletTransactionDto.builder()
                .amount(investRequestDto.getAmount())
                .email(principalUser.getEmail())
                .paymentType(PaymentType.INVEST)
                .paymentMethod(PaymentMethod.APP_TRANSFER)
                .relatedAccount("app_name")
                .build();
        WalletTransaction walletTransaction=walletTransactionService.saveTransaction(walletTransactionDto);
        userService.updateWallet(walletTransaction);
        //holding->user,mutualFund
        Holding holdingToSave= holdingOptional.isPresent()?holdingService.changeHoldingInvestment(holdingOptional.get(),walletTransaction,mutualFund)
                : holdingService.createHolding(principalUser, mutualFund, investRequestDto);
        Holding savedHolding=holdingService.saveHolding(holdingToSave);
        //holdingTransaction->holdings,user
        HoldingTransaction holdingTransactionToSave=holdingTransactionService.createHoldingTransaction(principalUser,mutualFund,walletTransaction,savedHolding );
        HoldingTransaction savedHoldingTransaction=holdingTransactionService.saveHoldingTransaction(holdingTransactionToSave);
        return savedHolding;
    }

}
