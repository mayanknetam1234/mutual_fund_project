package com.mayank.mutualFund.authentication.service;


import com.mayank.mutualFund.authentication.dto.VerifyUserRequestDto;
import com.mayank.mutualFund.authentication.entity.User;

public interface AuthenticationService {
    boolean isAuthentic(User user);
    void verifyOtp(VerifyUserRequestDto verifyUserRequestDto)throws RuntimeException;
}
