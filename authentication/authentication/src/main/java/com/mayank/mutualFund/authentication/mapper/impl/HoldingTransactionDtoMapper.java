package com.mayank.mutualFund.authentication.mapper.impl;

import com.mayank.mutualFund.authentication.dto.HoldingTransactionDto;
import com.mayank.mutualFund.authentication.entity.HoldingTransaction;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class HoldingTransactionDtoMapper implements Mapper<HoldingTransaction, HoldingTransactionDto> {
    private final ModelMapper modelMapper;

    public HoldingTransactionDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public HoldingTransaction convertToEntity(HoldingTransactionDto a) {
        return modelMapper.map(a,HoldingTransaction.class);
    }

    @Override
    public HoldingTransactionDto convertToDto(HoldingTransaction a) {
        return modelMapper.map(a, HoldingTransactionDto.class);
    }
}
