package com.mayank.mutualFund.authentication.entity;


import com.mayank.mutualFund.authentication.enumClasses.PaymentMethod;
import com.mayank.mutualFund.authentication.enumClasses.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "walletTransaction")
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_seq")
    private Long transactionId;
    private Double amount;
    @Enumerated(EnumType.STRING) // Stores as 'SELF_TRANSFER' or 'WITHDRAW'
    @Column(nullable = false)
    private PaymentType paymentType;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    private String relatedAccount;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // foreign key
    private User user;
}
//TODO: Add time stamp in transactions
//TODO : Add transaction status