package com.eloiza.bank_system.services;

import com.eloiza.bank_system.models.Account;
import com.eloiza.bank_system.events.AccountCreatedEvent;
import com.eloiza.bank_system.repositories.AccountRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AccountService(AccountRepository accountRepository, ApplicationEventPublisher eventPublisher) {
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public Account createAccount(String accountNumber, String ownerName) {
        Account account = new Account(accountNumber, ownerName);
        accountRepository.save(account);

        eventPublisher.publishEvent(new AccountCreatedEvent(accountNumber, ownerName));

        return account;
    }

    @Transactional
    public void deposit(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.deposit(amount);
        accountRepository.save(account);
    }

    @Transactional
    public void withdraw(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.withdraw(amount);
        accountRepository.save(account);
    }

    public BigDecimal getBalance(String accountNumber) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        return account.getBalance();
    }
}