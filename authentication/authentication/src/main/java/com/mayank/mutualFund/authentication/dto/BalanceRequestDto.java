package com.mayank.mutualFund.authentication.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BalanceRequestDto {
    private Integer pin;
    private String email;//Not to be sent by user
}
