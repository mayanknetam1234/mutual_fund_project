package com.mayank.mutualFund.authentication.dto;


import com.mayank.mutualFund.authentication.dto.embeddableDto.NavDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HoldingTransactionDto {
    private Long transactionId;
    private Double amount;
    private Double unitsAllocated;
    private NavDto nav;

}
