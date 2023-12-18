package com.mjd.bank.services;

import com.mjd.bank.dtos.response.SimpleMessageResponse;
import java.math.BigDecimal;

public interface AccountService {
  SimpleMessageResponse deposit(Long ownerId, Long accountId, BigDecimal amount);
}
