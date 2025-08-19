package com.mayank.mutualFund.authentication.controller;

import com.mayank.mutualFund.authentication.dto.MutualFundDto;
import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.mapper.Mapper;
import com.mayank.mutualFund.authentication.service.MutualFundService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/mutual-funds")
public class MutualFundsController {
    private final MutualFundService mutualFundService;
    private final Mapper<MutualFund,MutualFundDto> mutualFundDtoMapper;
    public MutualFundsController(MutualFundService mutualFundService, Mapper<MutualFund, MutualFundDto> mutualFundDtoMapper) {
        this.mutualFundService = mutualFundService;
        this.mutualFundDtoMapper = mutualFundDtoMapper;
    }

    @GetMapping("/user/explore")
    public ResponseEntity<Page<MutualFundDto>> sendAllMutualFunds(
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "0") Integer page
    ){
        Page<MutualFund> mutualFundPage=mutualFundService.getAllMutualFund(size,page);
        return new ResponseEntity<>(mutualFundPage.map(mutualFundDtoMapper::convertToDto), HttpStatus.OK);
    }

    @GetMapping("/user/{isin}")
    public ResponseEntity<?> getMutualFundByIsin(@PathVariable("isin") String isin){
        Optional<MutualFund> optionalMutualFund=mutualFundService.getMutualFundByIsin(isin);
        if(optionalMutualFund.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(mutualFundDtoMapper.convertToDto(optionalMutualFund.get()),HttpStatus.OK);
    }
}
