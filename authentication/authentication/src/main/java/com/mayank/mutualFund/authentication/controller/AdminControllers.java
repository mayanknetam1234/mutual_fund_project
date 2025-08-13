package com.mayank.mutualFund.authentication.controller;


import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.service.MutualFundApiService;
import com.mayank.mutualFund.authentication.service.MutualFundService;
import com.mayank.mutualFund.authentication.service.impl.CsvReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminControllers {
        private final MutualFundApiService mutualFundApiService;
        private final MutualFundService mutualFundService;
        private final CsvReaderService csvReaderService;

    public AdminControllers(MutualFundApiService mutualFundApiService, MutualFundService mutualFundService, CsvReaderService csvReaderService) {
        this.mutualFundApiService = mutualFundApiService;
        this.mutualFundService = mutualFundService;
        this.csvReaderService = csvReaderService;
    }

    @PutMapping("/api/v1/admin/mutualFunds/update")
    //TODO :- make it admin access only
    public ResponseEntity<?> updateMutualFunds() throws Exception {
        List<MutualFund> mutualFundList=mutualFundApiService.hitSuccessFullApi();
        mutualFundService.saveAll(mutualFundList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/api/v1/admin/mutualFunds/makeSuccessfulIsinList")
    //TODO :- make it admin access only
    public ResponseEntity<?> makeSuccessfulIsinList() throws Exception {
        mutualFundApiService.hitApiAndSaveIsin();

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
