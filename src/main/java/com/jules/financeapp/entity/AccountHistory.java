package com.jules.financeapp.entity;



import com.jules.financeapp.entity.enums.AccountEventType;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "account_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Compte concerné par l'événement
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    /**
     * Type d'événement (DEPOSIT, WITHDRAWAL, etc.)
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountEventType eventType;

    /**
     * Solde avant l'opération
     */
    @Column(precision = 19, scale = 2)
    private BigDecimal oldBalance;

    /**
     * Solde après l'opération
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal newBalance;

    /**
     * Raison lisible de l'opération
     */
    @Column(length = 255)
    private String reason;

    /**
     * Utilisateur ayant déclenché l'action
     */
    @Column(length = 100)
    private String changedBy;

    /**
     * Date de modification
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime changedAt;

    /**
     * Identifiant transaction (optionnel)
     */
    @Column(name = "transaction_id")
    private Long transactionId;

    /**
     * Adresse IP (optionnel, audit avancé)
     */
    @Column(length = 45)
    private String ipAddress;
}
