package com.mayank.mutualFund.authentication.controller;


import com.mayank.mutualFund.authentication.dto.HoldingDto;
import com.mayank.mutualFund.authentication.dto.InvestRequestDto;
import com.mayank.mutualFund.authentication.entity.*;
import com.mayank.mutualFund.authentication.enumClasses.PaymentMethod;
import com.mayank.mutualFund.authentication.enumClasses.PaymentType;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import com.mayank.mutualFund.authentication.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class MutualFundTransactionController {
    private final UserService userService;
    private final MutualFundService mutualFundService;
    private final Mapper<Holding, HoldingDto> holdingDtoMapper;
    private final TransactionService transactionService;


    public MutualFundTransactionController(UserService userService, MutualFundService mutualFundService, Mapper<Holding, HoldingDto> holdingDtoMapper, TransactionService transactionService) {
        this.userService = userService;
        this.mutualFundService = mutualFundService;
        this.holdingDtoMapper = holdingDtoMapper;
        this.transactionService = transactionService;

    }

    @PostMapping("/api/v1/mutual-fund/user/invest/{isin}")
    public ResponseEntity<?> investInAFund(@RequestBody InvestRequestDto investRequestDto, @PathVariable("isin") String isin) {
        //get the user
        Optional<User> principalUserOptional= userService.getPrincipleUser();

        if(principalUserOptional.isEmpty()) return  new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User principalUser=principalUserOptional.get();

        //check if user has enough money in wallet
        Double walletBalance=principalUser.getWallet()==null?0D:principalUser.getWallet();

        if( walletBalance<investRequestDto.getAmount()) return  new ResponseEntity<>("Not enough balance",HttpStatus.FORBIDDEN);

        Optional<MutualFund> mutualFundOptional=mutualFundService.getMutualFundByIsin(isin);

        if(mutualFundOptional.isEmpty()) return new ResponseEntity<>("No such mutualFundExists",HttpStatus.NOT_FOUND);

        //if all is correct then invest in holding and manage the transaction

        Holding responseHolding =transactionService.investInHoldingAndManageTransaction(principalUser,mutualFundOptional.get(),investRequestDto);

        return new ResponseEntity<>(holdingDtoMapper.convertToDto(responseHolding),HttpStatus.ACCEPTED);

    }



}
