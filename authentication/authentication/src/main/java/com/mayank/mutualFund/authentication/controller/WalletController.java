package com.mayank.mutualFund.authentication.controller;

import com.mayank.mutualFund.authentication.dto.AddMoneyRequestDto;
import com.mayank.mutualFund.authentication.dto.BalanceRequestDto;
import com.mayank.mutualFund.authentication.dto.BalanceResponseDto;
import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import com.mayank.mutualFund.authentication.enumClasses.PaymentType;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import com.mayank.mutualFund.authentication.service.TransactionService;
import com.mayank.mutualFund.authentication.service.UserService;
import com.mayank.mutualFund.authentication.service.WalletTransactionService;
import com.mayank.mutualFund.authentication.service.WalletValidationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class WalletController {
    private final UserService userService;
    private final TransactionService transactionService;
    private final Mapper<WalletTransaction,WalletTransactionDto> walletTransactionDtoMapper;
    private final WalletValidationService walletValidationService;
    public WalletController(UserService userService, TransactionService transactionService, Mapper<WalletTransaction, WalletTransactionDto> walletTransactionDtoMapper, WalletValidationService walletValidationService) {
        this.userService = userService;
        this.transactionService = transactionService;
        this.walletTransactionDtoMapper = walletTransactionDtoMapper;
        this.walletValidationService = walletValidationService;
    }

    @PostMapping("/api/v1/user/wallet/add")
    public ResponseEntity<WalletTransactionDto> addMoneyToWallet(@RequestBody WalletTransactionDto walletTransactionDto){
        //TODO : implement payment gateway
        walletTransactionDto.setEmail(userService.getEmailOfUser());
        walletTransactionDto.setPaymentType(PaymentType.SELF_TRANSFER);
        WalletTransaction walletTransaction=transactionService.manageBalanceOfUser(walletTransactionDto);
        return new ResponseEntity<>(walletTransactionDtoMapper.convertToDto(walletTransaction), HttpStatus.OK);
    }

    @PostMapping("/api/v1/user/wallet/withdraw")
    public ResponseEntity<?> withdrawMoneyFromWallet(@RequestBody WalletTransactionDto walletTransactionDto){
        //TODO: implement sending money from app to user
        //TODO: configure paymentMethod and relatedAccount field in WalletTransactionDto
        walletTransactionDto.setEmail(userService.getEmailOfUser());
        walletTransactionDto.setPaymentType(PaymentType.WITHDRAW);
        if(!walletValidationService.checkWithdrawAmount(walletTransactionDto.getEmail(),walletTransactionDto.getAmount())){
            return new ResponseEntity<>("Not enough balance",HttpStatus.FORBIDDEN);
        }
        WalletTransaction walletTransaction=transactionService.manageBalanceOfUser(walletTransactionDto);
        return new ResponseEntity<>(walletTransactionDtoMapper.convertToDto(walletTransaction), HttpStatus.OK);
    }

    @PostMapping("/api/v1/user/wallet/balance")
    public ResponseEntity<?> getWalletBalance(@RequestBody BalanceRequestDto balanceRequestDto){
        balanceRequestDto.setEmail(userService.getEmailOfUser());
        if(!walletValidationService.isPinCorrect(balanceRequestDto.getEmail(),balanceRequestDto.getPin())){
            return new ResponseEntity<>("Wrong pin",HttpStatus.UNAUTHORIZED);
        }
        //Everything is correct then get the balance and send it in the BalanceResponseDto
        Double currentBalance;
       try {
           currentBalance= userService.getAccountBalance(balanceRequestDto.getEmail());
       } catch (Exception e) {
           return new ResponseEntity<>("User not found",HttpStatus.UNAUTHORIZED);
       }
        BalanceResponseDto balanceResponseDto=BalanceResponseDto.builder()
                .currentBalance(currentBalance)
                .build();

        return new ResponseEntity<>(balanceResponseDto,HttpStatus.OK);
    }


}
