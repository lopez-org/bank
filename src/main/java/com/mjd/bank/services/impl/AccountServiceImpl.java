package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.Account;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.services.AccountService;
import com.mjd.bank.utils.TransactionsUtils;
import java.math.BigDecimal;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final TransactionsUtils transactionsUtils;
  private final AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository, TransactionsUtils transactionsUtils) {
    this.accountRepository = accountRepository;
    this.transactionsUtils = transactionsUtils;
  }

  @Override
  public SimpleMessageResponse deposit(Long ownerId, Long accountId, BigDecimal amount) {

    transactionsUtils.isAmountValid(amount);

    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new NotFoundException("Account not found"));

    transactionsUtils.isAccountOwner(ownerId, account.getOwner().getId());

    account.setBalance(account.getBalance().add(amount));
    accountRepository.save(account);

    return new SimpleMessageResponse(String.format("Deposit of %s to account %s completed", amount, accountId));
  }
}
