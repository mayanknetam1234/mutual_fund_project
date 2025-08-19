package com.mayank.mutualFund.authentication.dto;

import com.mayank.mutualFund.authentication.dto.embeddableDto.NavDto;
import com.mayank.mutualFund.authentication.dto.embeddableDto.ReturnsDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MutualFundDto {
    private String ISIN;
    private String name;
    private NavDto nav;
    private String category;
    private String fundType;
    private Long sipMin;
    private ReturnsDto returns;
    private Double lockInPeriod;
}
