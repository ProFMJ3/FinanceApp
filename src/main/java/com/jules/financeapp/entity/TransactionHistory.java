package com.jules.financeapp.entity;



import com.jules.financeapp.entity.enums.TransactionStatus;
import com.jules.financeapp.entity.enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Référence unique de la transaction
     */
    @Column(nullable = false, unique = true, length = 50)
    private String reference;

    /**
     * Compte source (null pour dépôt)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_account_id")
    private Account sourceAccount;

    /**
     * Compte destination (null pour retrait)
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destination_account_id")
    private Account destinationAccount;

    /**
     * Type de transaction
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    /**
     * Montant de la transaction
     */
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal amount;

    /**
     * Statut de la transaction
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    /**
     * Description ou motif
     */
    @Column(length = 255)
    private String description;

    /**
     * Utilisateur ayant initié la transaction
     */
    @Column(length = 100)
    private String initiatedBy;

    /**
     * Date de création
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Date de finalisation
     */
    private LocalDateTime completedAt;

    /**
     * Adresse IP (audit & sécurité)
     */
    @Column(length = 45)
    private String ipAddress;
}
