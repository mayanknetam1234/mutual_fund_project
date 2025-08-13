package com.mayank.mutualFund.adminApp.service.impl;


import com.mayank.mutualFund.adminApp.entity.csvEntity.MutualFundCsv;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.util.List;

@Service
public class CsvReaderService {
    public List<MutualFundCsv> readFunds(String fileName) {
        try {
            return new CsvToBeanBuilder<MutualFundCsv>(
                    new InputStreamReader(new ClassPathResource(fileName).getInputStream()))
                    .withType(MutualFundCsv.class)
                    .build()
                    .parse();

        } catch (Exception e) {
            return List.of();
        }
    }
}
