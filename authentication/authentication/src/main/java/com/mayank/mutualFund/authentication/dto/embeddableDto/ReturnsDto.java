package com.mayank.mutualFund.authentication.dto.embeddableDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReturnsDto {
    private double year1;
    private double year3;
    private double year5;
}
