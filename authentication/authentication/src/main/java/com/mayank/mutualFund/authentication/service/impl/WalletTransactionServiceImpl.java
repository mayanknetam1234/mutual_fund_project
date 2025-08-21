package com.mayank.mutualFund.authentication.service.impl;

import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import com.mayank.mutualFund.authentication.repository.WalletTransactionRepository;
import com.mayank.mutualFund.authentication.service.WalletTransactionService;
import org.springframework.stereotype.Service;

@Service
public class WalletTransactionServiceImpl implements WalletTransactionService {
    private final Mapper<WalletTransaction,WalletTransactionDto> walletTransactionDtoMapper;
    private final WalletTransactionRepository walletTransactionRepository;
    public WalletTransactionServiceImpl(Mapper<WalletTransaction, WalletTransactionDto> walletTransactionDtoMapper, WalletTransactionRepository walletTransactionRepository) {
        this.walletTransactionDtoMapper = walletTransactionDtoMapper;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    @Override
    public WalletTransaction saveTransaction(WalletTransactionDto walletTransactionDto) {
        //convert dto to entity
        WalletTransaction walletTransaction=walletTransactionDtoMapper.convertToEntity(walletTransactionDto);

        //save the transaction and return it;
        return walletTransactionRepository.save(walletTransaction);
    }
}
