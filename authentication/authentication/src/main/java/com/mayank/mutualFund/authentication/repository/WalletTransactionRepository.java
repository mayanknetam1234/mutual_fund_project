package com.mayank.mutualFund.authentication.repository;

import com.mayank.mutualFund.authentication.entity.WalletTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {

//    Page<WalletTransaction> findByUser_UserId(Long userId, Pageable pageable);
}
