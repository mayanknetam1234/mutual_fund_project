package com.mayank.mutualFund.authentication.service;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ErrorResponseService {
    void setUnauthorizedResponse(HttpServletResponse response,String message) throws IOException;
}
