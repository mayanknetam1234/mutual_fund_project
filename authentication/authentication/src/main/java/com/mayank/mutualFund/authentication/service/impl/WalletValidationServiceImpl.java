package com.mayank.mutualFund.authentication.service.impl;

import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.service.UserService;
import com.mayank.mutualFund.authentication.service.WalletValidationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletValidationServiceImpl implements WalletValidationService {
    private final UserService userService;

    public WalletValidationServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Boolean checkWithdrawAmount(String email, Double withdrawAmount) {
        Optional<User> user=userService.getUserByEmail(email);
        return user.isPresent() && user.get().getWallet() >= withdrawAmount;
    }

    @Override
    public Boolean isPinCorrect(String email, Integer pin) {
        //TODO: Implement validation to check pin
        return true;
    }
}
