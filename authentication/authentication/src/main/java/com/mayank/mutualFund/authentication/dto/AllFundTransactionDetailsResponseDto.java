package com.mayank.mutualFund.authentication.dto;

import com.mayank.mutualFund.authentication.entity.embedable.Nav;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllFundTransactionDetailsResponseDto {
    private Long transactionId;
    private Double amount;
    private Double unitsAllocated;
    private Nav nav;
    private String mutualFundName;
}
