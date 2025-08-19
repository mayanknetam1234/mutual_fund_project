package com.mayank.mutualFund.authentication.mapper.impl;

import com.mayank.mutualFund.authentication.dto.MutualFundDto;
import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class MutualFundDtoMapper implements Mapper<MutualFund, MutualFundDto> {
    private final ModelMapper modelMapper;

    public MutualFundDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MutualFund convertToEntity(MutualFundDto a) {
        return modelMapper.map(a,MutualFund.class);
    }

    @Override
    public MutualFundDto convertToDto(MutualFund a) {
        return modelMapper.map(a,MutualFundDto.class);
    }
}
