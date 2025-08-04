package com.mayank.mutualFund.authentication.mapper.impl;


import com.mayank.mutualFund.authentication.dto.UserDto;
import com.mayank.mutualFund.authentication.entity.User;

import com.mayank.mutualFund.authentication.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
public class UserDtoMapper implements Mapper<User, UserDto> {
    private final ModelMapper modelMapper;

    public UserDtoMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public User convertToEntity(UserDto a) {
        return modelMapper.map(a, User.class);
    }

    @Override
    public UserDto convertToDto(User a) {
        return modelMapper.map(a, UserDto.class);
    }
}
