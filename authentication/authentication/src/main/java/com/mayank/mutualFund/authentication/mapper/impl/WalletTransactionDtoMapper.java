package com.mayank.mutualFund.authentication.mapper.impl;

import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class WalletTransactionDtoMapper implements Mapper<WalletTransaction, WalletTransactionDto> {
    private final ModelMapper modelMapper;

    public WalletTransactionDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public WalletTransaction convertToEntity(WalletTransactionDto a) {
        return modelMapper.map(a, WalletTransaction.class);
    }

    @Override
    public WalletTransactionDto convertToDto(WalletTransaction a) {
        return modelMapper.map(a, WalletTransactionDto.class);
    }
}
