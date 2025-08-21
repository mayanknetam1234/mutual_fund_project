package com.mayank.mutualFund.authentication.config;

import com.mayank.mutualFund.authentication.dto.WalletTransactionDto;
import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import com.mayank.mutualFund.authentication.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(UserService userService){
        ModelMapper modelMapper=new ModelMapper();


        modelMapper.typeMap(WalletTransaction.class, WalletTransactionDto.class)
                .addMapping(src -> src.getUser().getEmail(), WalletTransactionDto::setEmail);


        modelMapper.typeMap(WalletTransactionDto.class, WalletTransaction.class)
                .addMappings(mapper -> mapper.skip(WalletTransaction::setUser)) // prevent default
                .setPostConverter(context -> {
                    WalletTransactionDto dto = context.getSource();
                    WalletTransaction transaction = context.getDestination();

                    // Build User only from userName
                    Optional<User> optionalUser=userService.getUserByEmail(dto.getEmail());
                    optionalUser.ifPresent(transaction::setUser);
                    return transaction;
                });


        return modelMapper;
    }
}
