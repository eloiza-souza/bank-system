package com.eloiza.bank_system.repositories;


import com.eloiza.bank_system.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {}