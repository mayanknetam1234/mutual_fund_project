package com.mayank.mutualFund.authentication.repository;


import com.mayank.mutualFund.authentication.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);


    boolean existsByUsername(String userName);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
