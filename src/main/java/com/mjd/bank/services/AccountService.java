package com.mjd.bank.services;

import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.AccountType;
import com.mjd.bank.entities.AppUser;

import java.math.BigDecimal;

public interface AccountService {
  SimpleMessageResponse deposit(Long ownerId, Long accountId, BigDecimal amount);

  SimpleMessageResponse create(Long ownerId, AccountType type);
}
