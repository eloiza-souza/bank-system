package com.eloiza.bank_system.models;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @ManyToOne
    private Account account;

    public Transaction() {}

    public Transaction(String type, BigDecimal amount, Account account) {
        this.type = type;
        this.amount = amount;
        this.account = account;
        this.timestamp = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Account getAccount() {
        return account;
    }
}