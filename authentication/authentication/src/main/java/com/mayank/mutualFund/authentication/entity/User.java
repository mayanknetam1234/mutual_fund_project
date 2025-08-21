package com.mayank.mutualFund.authentication.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    private Long userid;
    @Column( nullable = false,unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String email;
    private String verificationCode;
    private LocalDateTime verificationExpiresAt;
    private Boolean enabled;
    private Double wallet;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY) //  lazy load
    private List<WalletTransaction> walletTransactions;
}
//TODO:add bank details such as acc. no, card detail