package com.mayank.mutualFund.authentication.service.impl;


import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.repository.MutualFundRepository;
import com.mayank.mutualFund.authentication.service.MutualFundService;
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
