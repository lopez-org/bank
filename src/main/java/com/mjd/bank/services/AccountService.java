package com.mjd.bank.services;

import com.mjd.bank.dtos.request.CreationRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;

import java.math.BigDecimal;

public interface AccountService {
  SimpleMessageResponse deposit(Long ownerId, Long accountId, BigDecimal amount);
  SimpleMessageResponse create(Long ownerId, CreationRequest creationRequest);
}
