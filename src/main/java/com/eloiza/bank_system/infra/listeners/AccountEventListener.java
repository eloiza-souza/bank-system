package com.eloiza.bank_system.infra.listeners;

import com.eloiza.bank_system.events.AccountCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AccountEventListener {

    @Async // Processa o evento de forma assíncrona
    @EventListener
    public void handleAccountCreatedEvent(AccountCreatedEvent event) {
        System.out.println("Account created: " + event.getAccountNumber() + " for " + event.getOwnerName());
        // Simula o envio de uma notificação
        sendNotification(event);
    }

    private void sendNotification(AccountCreatedEvent event) {
        // Lógica para enviar notificação (e.g., e-mail, SMS)
        System.out.println("Notification sent to " + event.getOwnerName());
    }
}
