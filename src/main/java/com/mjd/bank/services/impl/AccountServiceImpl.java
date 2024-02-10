package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.request.accountDTO.AccountCreationRequest;
import com.mjd.bank.dtos.request.accountDTO.AccountDepositRequest;
import com.mjd.bank.dtos.response.AccountDetailDTO;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.AccountType;
import com.mjd.bank.entities.AppUser;
import com.mjd.bank.entities.TransactionStatus;
import com.mjd.bank.entities.TransactionType;
import com.mjd.bank.exceptions.IncorrectAccountTypeException;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.repositories.TransactionRepository;
import com.mjd.bank.services.AccountService;
import com.mjd.bank.services.AppUserService;
import com.mjd.bank.utils.TransactionsUtils;
import com.mjd.bank.utils.mappers.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

  private final AccountRepository accountRepository;
  private final AppUserService appUserService;
  private final AccountMapper accountMapper;
  private final TransactionRepository transactionRepository;
  private final TransactionsUtils transactionsUtils;

  @Autowired
  public AccountServiceImpl(AccountRepository accountRepository, AppUserService appUserService, TransactionsUtils transactionsUtils,
      AccountMapper accountMapper, TransactionRepository transactionRepository) {
    this.accountRepository = accountRepository;
    this.appUserService = appUserService;
    this.transactionsUtils = transactionsUtils;
    this.accountMapper = accountMapper;
    this.transactionRepository = transactionRepository;
  }

  @Override
  public SimpleMessageResponse deposit(Long ownerId, AccountDepositRequest depositRequest) {

    transactionsUtils.isAmountValid(depositRequest.getAmount());

    Account account = getAccountById(depositRequest.getAccountNumber());

    transactionsUtils.isAccountOwner(ownerId, account.getOwner().getId());

    account.setBalance(account.getBalance().add(depositRequest.getAmount()));
    save(account);
    transactionRepository.save(
        transactionsUtils.buildTransaction(
            account,
            null,
            depositRequest.getAmount(),
            TransactionType.DEPOSIT,
            TransactionStatus.COMPLETED,
            "Deposit to account"
        )
    );

    return new SimpleMessageResponse(
        String.format("Deposit of %s to account %s completed", depositRequest.getAmount(), depositRequest.getAccountNumber())
    );
  }

  @Override
  public AccountDetailDTO getAccountDetail(Long ownerId, Long accountId) {

    Account account = getAccountById(accountId);

    transactionsUtils.isAccountOwner(ownerId, account.getOwner().getId());

    return accountMapper.toAccountDetailDTO(account);
  }

  @Override
  public SimpleMessageResponse create(Long ownerId, AccountCreationRequest creationRequest) {

    AppUser owner = appUserService.getUserById(creationRequest.getOwnerId());

    transactionsUtils.isAccountOwner(ownerId, creationRequest.getOwnerId());

    if (AccountType.getAccountType(creationRequest.getType().toUpperCase()) == AccountType.UNKNOWN) {
      throw new IncorrectAccountTypeException("The account type doesn't exist");
    }

    Account account = new Account(AccountType.getAccountType(creationRequest.getType().toUpperCase()), owner);
    save(account);
    return new SimpleMessageResponse("Account created successfully");
  }

  @Override
  public Account getAccountById(Long id) {
    return accountRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Account not found"));
  }

  @Override
  public void save(Account account) {
    accountRepository.save(account);
  }


}
