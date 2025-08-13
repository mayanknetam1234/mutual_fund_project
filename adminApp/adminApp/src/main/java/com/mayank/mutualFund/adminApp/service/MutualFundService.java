package com.mayank.mutualFund.adminApp.service;

import com.mayank.mutualFund.adminApp.entity.MutualFund;

import java.util.List;

public interface MutualFundService {
    void saveAll(List<MutualFund> mutualFundList);
}
