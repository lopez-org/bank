package com.mjd.bank.controllers;

import com.mjd.bank.dtos.request.CreateRequest;
import com.mjd.bank.dtos.request.DepositRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.AccountType;
import com.mjd.bank.services.AccountService;
import java.math.BigDecimal;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accounts")
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping ("/deposit")
  public SimpleMessageResponse deposit(@RequestHeader(name = "owner_id") Long ownerId, @RequestBody DepositRequest depositRequest) {
    return accountService.deposit(ownerId, depositRequest.getAccountNumber(), depositRequest.getAmount());
  }

  @PostMapping ("/create")
  public SimpleMessageResponse create(@RequestHeader(name = "owner_id") Long ownerId, @RequestBody CreateRequest type) {
    return accountService.create(ownerId, AccountType.getAccountType(type.getType().toUpperCase()));
  }
}
