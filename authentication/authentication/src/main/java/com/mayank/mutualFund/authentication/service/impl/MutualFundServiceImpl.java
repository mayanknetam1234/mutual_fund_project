package com.mayank.mutualFund.authentication.service.impl;


import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.repository.MutualFundRepository;
import com.mayank.mutualFund.authentication.service.MutualFundService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Page<MutualFund> getAllMutualFund(Integer size, Integer page) {
        Pageable pageable = PageRequest.of(page, size);
        return mutualFundRepository.findAll(pageable);
    }

    @Override
    public Optional<MutualFund> getMutualFundByIsin(String isin) {
        return mutualFundRepository.findById(isin);
    }
}
