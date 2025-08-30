package com.mayank.mutualFund.authentication.mapper.impl;

import com.mayank.mutualFund.authentication.dto.AllFundTransactionDetailsResponseDto;
import com.mayank.mutualFund.authentication.entity.HoldingTransaction;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AllFundTransactionDetailsDtoMapper implements Mapper<HoldingTransaction, AllFundTransactionDetailsResponseDto> {
    private final ModelMapper modelMapper;

    public AllFundTransactionDetailsDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public HoldingTransaction convertToEntity(AllFundTransactionDetailsResponseDto a) {
        return modelMapper.map(a, HoldingTransaction.class);
    }

    @Override
    public AllFundTransactionDetailsResponseDto convertToDto(HoldingTransaction a) {
        return modelMapper.map(a, AllFundTransactionDetailsResponseDto.class);
    }
}
