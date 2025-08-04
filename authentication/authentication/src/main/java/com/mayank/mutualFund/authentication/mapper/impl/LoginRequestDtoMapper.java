package com.mayank.mutualFund.authentication.mapper.impl;

import com.mayank.mutualFund.authentication.dto.LoginRequestDto;
import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class LoginRequestDtoMapper implements Mapper<User, LoginRequestDto> {

    private final ModelMapper modelMapper;

    public LoginRequestDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public User convertToEntity(LoginRequestDto a) {
        return modelMapper.map(a, User.class);
    }

    @Override
    public LoginRequestDto convertToDto(User a) {
        return modelMapper.map(a,LoginRequestDto.class);
    }
}
