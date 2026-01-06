package com.jules.financeapp.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Table(name = "audit_log")
@Entity
public class AuditLog {

    @Id
    @GeneratedValue
    private Long id;

    private String entityName; // User, Account, Transaction
    private Long entityId;

    private String action;

    @Column(columnDefinition = "TEXT")
    private String oldValue;

    @Column(columnDefinition = "TEXT")
    private String newValue;

    private String performedBy;   // username
    private String performedRole; // ADMIN / CLIENT

    private LocalDateTime timestamp;
}

