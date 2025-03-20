package com.eloiza.bank_system.services;

import com.eloiza.bank_system.models.Account;
import com.eloiza.bank_system.models.Transaction;
import com.eloiza.bank_system.repositories.AccountRepository;
import com.eloiza.bank_system.repositories.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository) {
        this.accountRepository = accountRepository;
        this.transactionRepository = transactionRepository;
    }

    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        Account fromAccount = accountRepository.findByAccountNumber(fromAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        Account toAccount = accountRepository.findByAccountNumber(toAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);

        transactionRepository.save(new Transaction("TRANSFER_OUT", amount, fromAccount));
        transactionRepository.save(new Transaction("TRANSFER_IN", amount, toAccount));
    }
}