package com.mayank.mutualFund.authentication.dto;


import com.mayank.mutualFund.authentication.enumClasses.PaymentMethod;
import com.mayank.mutualFund.authentication.enumClasses.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMoneyRequestDto {
    private PaymentType paymentType;
    private PaymentMethod paymentMethod;
    private String relatedAccount;
    private Double amount;
}
