package com.mayank.mutualFund.authentication.controller;

import com.mayank.mutualFund.authentication.dto.AddMoneyRequestDto;
import com.mayank.mutualFund.authentication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {
    private final UserService userService;

    public WalletController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping("/api/v1/user/wallet/add")
    public ResponseEntity<?> addMoneyToWallet(@RequestBody AddMoneyRequestDto addMoneyRequestDto){
        //TODO : implement payment gateway

        return new ResponseEntity<>("adsf", HttpStatus.OK);
    }
}
