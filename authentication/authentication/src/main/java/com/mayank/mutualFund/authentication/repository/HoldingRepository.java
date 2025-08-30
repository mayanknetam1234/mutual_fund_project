package com.mayank.mutualFund.authentication.repository;

import com.mayank.mutualFund.authentication.entity.Holding;
import com.mayank.mutualFund.authentication.entity.MutualFund;
import com.mayank.mutualFund.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoldingRepository extends JpaRepository<Holding,Long> {
    Optional<Holding> findByUserAndMutualFund(User user, MutualFund mutualFund);
    Iterable<Holding> findByUser(User user);
}
