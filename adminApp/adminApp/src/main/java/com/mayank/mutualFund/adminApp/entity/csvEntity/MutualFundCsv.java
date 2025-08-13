package com.mayank.mutualFund.adminApp.entity.csvEntity;

import com.opencsv.bean.CsvBindByName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MutualFundCsv {
    @CsvBindByName
    private String ISIN;
}
