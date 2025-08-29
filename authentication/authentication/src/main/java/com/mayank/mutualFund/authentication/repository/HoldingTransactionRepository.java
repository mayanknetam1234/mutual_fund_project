package com.mayank.mutualFund.authentication.repository;

import com.mayank.mutualFund.authentication.entity.HoldingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldingTransactionRepository extends JpaRepository<HoldingTransaction,Long> {
}
