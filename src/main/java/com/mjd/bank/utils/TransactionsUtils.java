package com.mjd.bank.utils;

import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.Transaction;
import com.mjd.bank.entities.TransactionStatus;
import com.mjd.bank.entities.TransactionType;
import com.mjd.bank.exceptions.IncorrectAmountException;
import com.mjd.bank.exceptions.NotOwnerException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class TransactionsUtils {

    private TransactionsUtils() {
    }

    public void isAmountValid(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IncorrectAmountException("Amount must be greater than 0");
        }
    }

    public void isAccountOwner(Long ownerId, Long accountOwnerId) {
        //TODO: Corregir cuando implementemos spring security para usar authorities
        if (!ownerId.equals(accountOwnerId)) {
            throw new NotOwnerException("You are not the owner of this account");
        }
    }

    public void isPocketTransferValid(BigDecimal accountBalance, BigDecimal amount) {
        if (accountBalance.subtract(amount).intValue() < 0) {
            throw new IncorrectAmountException("Account balance is not enough to complete this transaction. Please try again with a lower amount");
        }
    }

    public Transaction buildTransaction(Account from, Account to, BigDecimal amount, TransactionType type, TransactionStatus status, String description) {
        return new Transaction()
            .setFrom(from)
            .setTo(to)
            .setAmount(amount)
            .setType(type)
            .setDescription(description)
            .setStatus(status)
            .setCreatedAt(LocalDateTime.now());
    }
}
