package com.mayank.mutualFund.authentication.controller;


import com.mayank.mutualFund.authentication.dto.*;
import com.mayank.mutualFund.authentication.entity.Holding;
import com.mayank.mutualFund.authentication.entity.HoldingTransaction;
import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import com.mayank.mutualFund.authentication.service.HoldingService;
import com.mayank.mutualFund.authentication.service.HoldingTransactionService;
import com.mayank.mutualFund.authentication.service.UserService;
import com.mayank.mutualFund.authentication.service.WalletTransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

@RestController
public class TransactionDetailsController {
    private final UserService userService;
    private final HoldingService holdingService;
    private final HoldingTransactionService holdingTransactionService;
    private final Mapper<Holding,HoldingDto> holdingDtoMapper;
    private final Mapper<HoldingTransaction,HoldingTransactionDto> holdingTransactionDtoMapper;
    private final Mapper<HoldingTransaction,AllFundTransactionDetailsResponseDto> allFundTransactionDetailsResponseDtoMapper;
    private final WalletTransactionService walletTransactionService;
    private final Mapper<WalletTransaction,WalletTransactionDto> walletTransactionDtoMapper;
    public TransactionDetailsController(UserService userService, HoldingService holdingService, HoldingTransactionService holdingTransactionService, Mapper<Holding, HoldingDto> holdingDtoMapper, Mapper<HoldingTransaction, HoldingTransactionDto> holdingTransactionDtoMapper, Mapper<HoldingTransaction, AllFundTransactionDetailsResponseDto> allFundTransactionDetailsResponseDtoMapper, WalletTransactionService walletTransactionService, Mapper<WalletTransaction, WalletTransactionDto> walletTransactionDtoMapper) {
        this.userService = userService;
        this.holdingService = holdingService;
        this.holdingTransactionService = holdingTransactionService;
        this.holdingDtoMapper = holdingDtoMapper;
        this.holdingTransactionDtoMapper = holdingTransactionDtoMapper;
        this.allFundTransactionDetailsResponseDtoMapper = allFundTransactionDetailsResponseDtoMapper;
        this.walletTransactionService = walletTransactionService;
        this.walletTransactionDtoMapper = walletTransactionDtoMapper;
    }

    @GetMapping("/api/v1/investment-details/{holdingId}")
    public ResponseEntity<?> getTransactionDetailOfAFund(@PathVariable("holdingId") Long holdingId){
        Optional<User> userOptional=userService.getPrincipleUser();
        Optional<Holding> holdingOptional=holdingService.getHoldingByHoldingId(holdingId);

        if(userOptional.isEmpty() || holdingOptional.isEmpty() || !Objects.equals(holdingOptional.get().getUser().getUserid(), userOptional.get().getUserid())) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        User principleUser=userOptional.get();
        Holding principleHolding=holdingOptional.get();

        Iterable<HoldingTransaction> holdingTransactionIterable=holdingTransactionService.getAllHoldingTransactionByUserAndHolding(principleUser,principleHolding);

        /*
        {holdingDetail:holdingDto,
        holdingTransaction:List<HoldingTransactionDto>
        }
        * */
        HoldingDto holdingDto=holdingDtoMapper.convertToDto(principleHolding);
        List<HoldingTransactionDto>  holdingTransactionDtoList= StreamSupport.stream(holdingTransactionIterable.spliterator(), false)
                .map(holdingTransactionDtoMapper::convertToDto)
                .toList();


        FundTransactionDetailsResponseDto fundTransactionDetailsResponseDto=FundTransactionDetailsResponseDto.builder()
                .holdingDetails(holdingDto)
                .transactionDetailsOfHolding(holdingTransactionDtoList)
                .build();

        return new ResponseEntity<>(fundTransactionDetailsResponseDto,HttpStatus.OK);
    }

    @GetMapping("/api/v1/user/order/mutual-funds")
    //TODO : make it return transaction in pages
    public ResponseEntity<List<AllFundTransactionDetailsResponseDto>> getAllMutualFundTransaction(){
        Optional<User> userOptional=userService.getPrincipleUser();
        if(userOptional.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User principleUser=userOptional.get();
        Iterable<HoldingTransaction> holdingTransactionIterable=holdingTransactionService.getAllHoldingTransactionByUser(principleUser);

        /*

        [
        AlFundTransactionDetailsDto
        ]
        * */
        List<AllFundTransactionDetailsResponseDto>  allFundTransactionDetailsResponseDtoList= StreamSupport.stream(holdingTransactionIterable.spliterator(), false)
                .map(allFundTransactionDetailsResponseDtoMapper::convertToDto)
                .toList();

        return new ResponseEntity<>(allFundTransactionDetailsResponseDtoList,HttpStatus.OK);
    }

    @GetMapping("/api/user/order/wallet")
    public ResponseEntity<?>  getAllWalletTransaction(){
        Optional<User> userOptional=userService.getPrincipleUser();
        if(userOptional.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User principleUser=userOptional.get();
        Iterable<WalletTransaction> walletTransactionIterable=walletTransactionService.getAllWalletTransactionByUser(principleUser);

        /*
        [
        WalletTransactionDto
        ]
        * */
        List<WalletTransactionDto>  walletTransactionDtoList= StreamSupport.stream(walletTransactionIterable.spliterator(), false)
                .map(walletTransactionDtoMapper::convertToDto)
                .toList();

        return new ResponseEntity<>(walletTransactionDtoList,HttpStatus.OK);
    }
}
