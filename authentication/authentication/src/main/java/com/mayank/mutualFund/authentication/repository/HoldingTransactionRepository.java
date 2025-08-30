package com.mayank.mutualFund.authentication.repository;

import com.mayank.mutualFund.authentication.entity.Holding;
import com.mayank.mutualFund.authentication.entity.HoldingTransaction;
import com.mayank.mutualFund.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoldingTransactionRepository extends JpaRepository<HoldingTransaction,Long> {
    Iterable<HoldingTransaction> findByUserAndHolding(User user, Holding holding);
    Iterable<HoldingTransaction> findByUser(User user);
}
