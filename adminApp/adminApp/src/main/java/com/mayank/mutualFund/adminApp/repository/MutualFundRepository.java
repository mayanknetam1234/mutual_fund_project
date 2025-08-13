package com.mayank.mutualFund.adminApp.repository;

import com.mayank.mutualFund.adminApp.entity.MutualFund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutualFundRepository extends JpaRepository<MutualFund,String> {
}
