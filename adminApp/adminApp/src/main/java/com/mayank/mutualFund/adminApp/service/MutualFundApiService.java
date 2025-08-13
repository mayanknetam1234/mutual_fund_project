package com.mayank.mutualFund.adminApp.service;


import com.mayank.mutualFund.adminApp.entity.MutualFund;

import java.util.List;

public interface MutualFundApiService {

    void hitApiAndSaveIsin(Integer hitNo,String fileName) throws Exception;
    List<MutualFund> hitSuccessFullApi(String fileName);

}
