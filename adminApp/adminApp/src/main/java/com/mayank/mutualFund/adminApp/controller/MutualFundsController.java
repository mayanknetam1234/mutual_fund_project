package com.mayank.mutualFund.adminApp.controller;


import com.mayank.mutualFund.adminApp.entity.MutualFund;
import com.mayank.mutualFund.adminApp.entity.csvEntity.MutualFundCsv;
import com.mayank.mutualFund.adminApp.service.MutualFundApiService;
import com.mayank.mutualFund.adminApp.service.MutualFundService;
import com.mayank.mutualFund.adminApp.service.impl.CsvReaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MutualFundsController {
        private final MutualFundApiService mutualFundApiService;
        private final MutualFundService mutualFundService;
        private final CsvReaderService csvReaderService;

    public MutualFundsController(MutualFundApiService mutualFundApiService, MutualFundService mutualFundService, CsvReaderService csvReaderService) {
        this.mutualFundApiService = mutualFundApiService;
        this.mutualFundService = mutualFundService;
        this.csvReaderService = csvReaderService;
    }

    @PutMapping("/mutualFunds/update/{fileName}")
    public ResponseEntity<?> updateMutualFunds(@PathVariable String fileName) throws Exception {
        List<MutualFund> mutualFundList=mutualFundApiService.hitSuccessFullApi(fileName);
        mutualFundService.saveAll(mutualFundList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/mutualFunds/makeIsinList")
    public ResponseEntity<?> makeIsinList(@RequestParam(name = "hitNo") Integer hitNo,@RequestParam(name = "fileName") String fileName) throws Exception {
        mutualFundApiService.hitApiAndSaveIsin(hitNo,fileName);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
