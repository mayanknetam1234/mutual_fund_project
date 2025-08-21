package com.mayank.mutualFund.authentication.service.impl;

import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import com.mayank.mutualFund.authentication.repository.UserRepository;
import com.mayank.mutualFund.authentication.service.TransactionService;
import com.mayank.mutualFund.authentication.service.UserService;
import com.mayank.mutualFund.authentication.service.WalletTransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final WalletTransactionService walletTransactionService;
    private final UserService userService;
    public TransactionServiceImpl(WalletTransactionService walletTransactionService, UserService userService) {
        this.walletTransactionService = walletTransactionService;

        this.userService = userService;
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
}
