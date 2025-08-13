package com.mayank.mutualFund.adminApp.service.impl;

import com.mayank.mutualFund.adminApp.entity.MutualFund;
import com.mayank.mutualFund.adminApp.entity.csvEntity.MutualFundCsv;
import com.mayank.mutualFund.adminApp.repository.MutualFundRepository;
import com.mayank.mutualFund.adminApp.service.MutualFundApiService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


import java.util.ArrayList;
import java.util.List;


@Service
public class MutualFundApiServiceImpl implements MutualFundApiService {
    private final CsvReaderService csvReaderService;
    private final RestTemplate restTemplate;
    private final MutualFundRepository mutualFundRepository;
    public MutualFundApiServiceImpl(CsvReaderService csvReaderService, RestTemplate restTemplate, MutualFundRepository mutualFundRepository) {
        this.csvReaderService = csvReaderService;
        this.restTemplate = restTemplate;
        this.mutualFundRepository = mutualFundRepository;
    }



    @Override
    public void hitApiAndSaveIsin(Integer hitNo,String fileName) throws Exception{
        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");
        String url = "https://mf.captnemo.in/kuvera/{isin}";
        List<MutualFundCsv> mutualFundCsvList=csvReaderService.readFunds("isin_list.csv");
       try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))){
           int i=0;
           writer.println("ISIN");
           for (MutualFundCsv mutualFundCsv : mutualFundCsvList) {
                if(i==hitNo){
                    break;
                }

               try {
                   MutualFund[] fund = restTemplate.getForObject(url, MutualFund[].class, mutualFundCsv.getISIN());

                   if (fund != null && fund.length > 0) {
                       System.out.println( fund[0].toString());
                       writer.println(mutualFundCsv.getISIN());
                   } else {
                       System.out.println("Empty or null fund for ISIN: " + mutualFundCsv.getISIN());
                   }

               } catch (HttpClientErrorException e) {
                   // Handles 404 and other 4xx errors
                   System.out.println("Client error for ISIN " + mutualFundCsv.getISIN() + ": " + e.getStatusCode());
               } catch (RestClientException e) {
                   // Catches all other errors (e.g. connection refused)
                   System.out.println("Request failed for ISIN " + mutualFundCsv.getISIN() + ": " + e.getMessage());
               }
               i++;

           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }

    @Override
    public List<MutualFund> hitSuccessFullApi(String fileName) {
        String url = "https://mf.captnemo.in/kuvera/{isin}";
        List<MutualFundCsv> mutualFundCsvList = csvReaderService.readFunds(fileName);
        List<MutualFund> mutualFundList = new ArrayList<>();
        for (MutualFundCsv mutualFundCsv : mutualFundCsvList) {

            try {
                MutualFund[] fund = restTemplate.getForObject(url, MutualFund[].class, mutualFundCsv.getISIN());

                if (fund != null && fund.length > 0) {
                    System.out.println(fund[0].toString());
                    mutualFundList.add(fund[0]);
                } else {
                    System.out.println("Empty or null fund for ISIN: " + mutualFundCsv.getISIN());
                }

            } catch (HttpClientErrorException e) {
                // Handles 404 and other 4xx errors
                System.out.println("Client error for ISIN " + mutualFundCsv.getISIN() + ": " + e.getStatusCode());
            } catch (RestClientException e) {
                // Catches all other errors (e.g. connection refused)
                System.out.println("Request failed for ISIN " + mutualFundCsv.getISIN() + ": " + e.getMessage());
            }

        }
        return mutualFundList;
    }
}

