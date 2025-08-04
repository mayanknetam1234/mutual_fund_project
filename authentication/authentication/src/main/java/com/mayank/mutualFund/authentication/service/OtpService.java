package com.mayank.mutualFund.authentication.service;

public interface OtpService {
    String generateOtp();
    void sendOtp(String email,String otp)throws RuntimeException;
    void reSendOtp(String email) throws RuntimeException;
}
