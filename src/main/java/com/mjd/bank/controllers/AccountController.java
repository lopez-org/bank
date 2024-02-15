package com.mjd.bank.controllers;

import com.mjd.bank.dtos.request.accountDTO.AccountCreationRequest;
import com.mjd.bank.dtos.request.accountDTO.AccountDepositRequest;
import com.mjd.bank.dtos.response.AccountDetailDTO;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.services.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

  private final AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  @PostMapping ("/deposit")
  public SimpleMessageResponse deposit(@RequestHeader(name = "owner_id") Long ownerId, @RequestBody AccountDepositRequest depositRequest) {
    return accountService.deposit(ownerId, depositRequest);
  }

  @GetMapping("/{id}")
  public AccountDetailDTO getAccountDetail(@RequestHeader(name = "owner_id") Long ownerId, @PathVariable(name = "id") Long accountId) {
    return accountService.getAccountDetail(ownerId, accountId);
  }

  @PostMapping ("/create")
  public SimpleMessageResponse create(@RequestHeader(name = "owner_id") Long ownerId, @RequestBody AccountCreationRequest creationRequest) {
    return accountService.create(ownerId, creationRequest);
  }
}
