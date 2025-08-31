package com.mayank.mutualFund.authentication.service;

import jakarta.servlet.http.HttpServletRequest;

public interface CookieService {
    String extractJwtFromCookies(HttpServletRequest request);
}
