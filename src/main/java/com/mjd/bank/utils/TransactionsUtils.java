package com.mjd.bank.utils;

import com.mjd.bank.exceptions.IncorrectAmountException;
import com.mjd.bank.exceptions.NotOwnerException;
import java.math.BigDecimal;
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
}
