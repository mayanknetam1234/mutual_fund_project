package com.mayank.mutualFund.adminApp.entity.embedable;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Returns {

    @JsonProperty("week_1")
    private double week1;

    @JsonProperty("year_1")
    private double year1;

    @JsonProperty("year_3")
    private double year3;

    @JsonProperty("year_5")
    private double year5;

    private double inception;
}