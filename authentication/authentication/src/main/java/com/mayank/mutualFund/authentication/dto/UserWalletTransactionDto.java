package com.mayank.mutualFund.authentication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserWalletTransactionDto {
    private String username;
    private String email;
    private List<WalletTransactionDto> walletTransaction;
}
