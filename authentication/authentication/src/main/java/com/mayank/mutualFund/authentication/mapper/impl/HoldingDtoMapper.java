package com.mayank.mutualFund.authentication.mapper.impl;

import com.mayank.mutualFund.authentication.dto.HoldingDto;
import com.mayank.mutualFund.authentication.entity.Holding;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class HoldingDtoMapper implements Mapper<Holding, HoldingDto> {
    private final ModelMapper modelMapper;

    public HoldingDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public Holding convertToEntity(HoldingDto a) {
        return modelMapper.map(a, Holding.class);
    }

    @Override
    public HoldingDto convertToDto(Holding a) {
        return modelMapper.map(a, HoldingDto.class);
    }
}
