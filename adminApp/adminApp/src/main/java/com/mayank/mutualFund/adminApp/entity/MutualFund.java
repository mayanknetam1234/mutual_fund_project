package com.mayank.mutualFund.adminApp.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mayank.mutualFund.adminApp.entity.embedable.Nav;
import com.mayank.mutualFund.adminApp.entity.embedable.Returns;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "mutualFunds")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MutualFund {
    @Id
    @JsonProperty("ISIN")
    private String ISIN;
    private String name;
    @Embedded
    private Nav nav;
    private String category;
    @JsonProperty("fund_type")
    private String fundType;
    @JsonProperty("sip_min")
    private Long sipMin;
    @Embedded
    private Returns returns;
    @JsonProperty("lock_in_period")
    private Double lockInPeriod;
}
