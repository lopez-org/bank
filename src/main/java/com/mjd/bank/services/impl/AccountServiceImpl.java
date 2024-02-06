package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.AccountType;
import com.mjd.bank.entities.AppUser;
import com.mjd.bank.exceptions.IncorrectAccountTypeException;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.repositories.AppUserRepository;
import com.mjd.bank.services.AccountService;
import com.mjd.bank.utils.TransactionsUtils;
import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final TransactionsUtils transactionsUtils;
  private final AccountRepository accountRepository;
  private final AppUserRepository appUserRepository;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository, TransactionsUtils transactionsUtils, AppUserRepository appUserRepository) {
    this.accountRepository = accountRepository;
    this.transactionsUtils = transactionsUtils;
    this.appUserRepository = appUserRepository;
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

  @Override
  public SimpleMessageResponse create(Long ownerId, AccountType type) {

    AppUser owner = appUserRepository.findById(ownerId)
            .orElseThrow(() ->new NotFoundException("The user with ID " + ownerId + " doesn't exist"));

      if (type!=AccountType.UNKNOWN){
        Account account = new Account(type,owner);
        accountRepository.save(account);
        return new SimpleMessageResponse("Account created successfully");
      }else {
        throw new IncorrectAccountTypeException("The account type doesn't exist");
      }
  }
}
