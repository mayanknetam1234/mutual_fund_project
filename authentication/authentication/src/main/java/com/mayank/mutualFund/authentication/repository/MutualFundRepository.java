package com.mayank.mutualFund.authentication.repository;


import com.mayank.mutualFund.authentication.entity.MutualFund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MutualFundRepository extends JpaRepository<MutualFund,String> {
}
