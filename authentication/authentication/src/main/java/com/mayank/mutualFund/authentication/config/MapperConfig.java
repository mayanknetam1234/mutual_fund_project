package com.mayank.mutualFund.authentication.config;

import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper=new ModelMapper();
        modelMapper.typeMap(WalletTransaction.class, WalletTransactionDto.class)
                .addMapping(src -> src.getUser().getEmail(), WalletTransactionDto::setEmail);

        return modelMapper;
    }
}
