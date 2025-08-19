package com.mayank.mutualFund.authentication.service;



import com.mayank.mutualFund.authentication.entity.MutualFund;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface MutualFundService {
    void saveAll(List<MutualFund> mutualFundList);
    Page<MutualFund> getAllMutualFund(Integer size,Integer page);

    Optional<MutualFund> getMutualFundByIsin(String isin);
}
