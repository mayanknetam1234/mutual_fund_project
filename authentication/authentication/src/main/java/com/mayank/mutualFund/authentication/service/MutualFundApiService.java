package com.mayank.mutualFund.authentication.service;




import com.mayank.mutualFund.authentication.entity.MutualFund;

import java.util.List;

public interface MutualFundApiService {

    void hitApiAndSaveIsin() throws Exception;
    List<MutualFund> hitSuccessFullApi();

}
