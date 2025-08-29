package com.mayank.mutualFund.authentication.entity;

import com.mayank.mutualFund.authentication.entity.embedable.Nav;
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
@Table(name = "holdingTransaction")
public class HoldingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "holdingTransaction_id_seq")
    private Long transactionId;
    private Double amount;
    private Double unitsAllocated;
    @Embedded
    private Nav nav;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "holding_id", nullable = false)
    private Holding holding;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // foreign key
    private User user;

}
//TODO : add status, time, type
