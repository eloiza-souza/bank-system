package com.eloiza.bank_system.events;


public class AccountCreatedEvent {
    private final String accountNumber;
    private final String ownerName;

    public AccountCreatedEvent(String accountNumber, String ownerName) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }
}