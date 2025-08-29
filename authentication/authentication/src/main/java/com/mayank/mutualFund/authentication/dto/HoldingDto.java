package com.mayank.mutualFund.authentication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoldingDto {
    private Long holdingId;
    private Double currentInvestment;
    private Double unitsAllocated;
    private String email;
    private MutualFundDto mutualFund;
}
