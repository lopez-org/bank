package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.response.AccountDetailDTO;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.Account;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.services.AccountService;
import com.mjd.bank.utils.TransactionsUtils;
import com.mjd.bank.utils.mappers.AccountMapper;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AccountMapper accountMapper;
  private final TransactionsUtils transactionsUtils;

  public AccountServiceImpl(
      AccountRepository accountRepository,
      TransactionsUtils transactionsUtils,
      AccountMapper accountMapper
  ) {
    this.accountRepository = accountRepository;
    this.transactionsUtils = transactionsUtils;
    this.accountMapper = accountMapper;
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
  public AccountDetailDTO getAccountDetail(Long ownerId, Long accountId) {

    Account account = accountRepository.findById(accountId)
        .orElseThrow(() -> new NotFoundException("Account not found"));

    transactionsUtils.isAccountOwner(ownerId, account.getOwner().getId());

    return accountMapper.toAccountDetailDTO(account);
  }
}
