package com.eloiza.bank_system.controllers;

import com.eloiza.bank_system.services.AccountService;
import com.eloiza.bank_system.services.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransactionService transactionService;

    public AccountController(AccountService accountService, TransactionService transactionService) {
        this.accountService = accountService;
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createAccount(@RequestParam String accountNumber, @RequestParam String ownerName) {
        accountService.createAccount(accountNumber, ownerName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{accountNumber}/deposit")
    public ResponseEntity<Void> deposit(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        accountService.deposit(accountNumber, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{accountNumber}/withdraw")
    public ResponseEntity<Void> withdraw(@PathVariable String accountNumber, @RequestParam BigDecimal amount) {
        accountService.withdraw(accountNumber, amount);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestParam String fromAccount, @RequestParam String toAccount, @RequestParam BigDecimal amount) {
        transactionService.transfer(fromAccount, toAccount, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{accountNumber}/balance")
    public ResponseEntity<BigDecimal> getBalance(@PathVariable String accountNumber) {
        return ResponseEntity.ok(accountService.getBalance(accountNumber));
    }
}
