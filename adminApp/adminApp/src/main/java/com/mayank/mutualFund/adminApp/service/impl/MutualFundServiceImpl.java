package com.mayank.mutualFund.adminApp.service.impl;

import com.mayank.mutualFund.adminApp.entity.MutualFund;
import com.mayank.mutualFund.adminApp.repository.MutualFundRepository;
import com.mayank.mutualFund.adminApp.service.MutualFundService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MutualFundServiceImpl implements MutualFundService {
    private final MutualFundRepository mutualFundRepository;

    public MutualFundServiceImpl(MutualFundRepository mutualFundRepository) {
        this.mutualFundRepository = mutualFundRepository;
    }

    @Override
    public void saveAll(List<MutualFund> mutualFundList) {
        mutualFundRepository.saveAll(mutualFundList);
    }
}
