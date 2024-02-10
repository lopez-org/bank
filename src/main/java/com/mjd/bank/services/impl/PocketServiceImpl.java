package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.request.pocketDTO.PocketCreationRequest;
import com.mjd.bank.dtos.request.pocketDTO.PocketTransferRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.Pocket;
import com.mjd.bank.exceptions.InvalidNameException;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.repositories.PocketRepository;
import com.mjd.bank.services.PocketService;
import com.mjd.bank.utils.TransactionsUtils;
import org.springframework.stereotype.Service;

@Service
public class PocketServiceImpl implements PocketService {

  private final AccountRepository accountRepository;
  private final PocketRepository pocketRepository;
  private final TransactionsUtils transactionsUtils;

  public PocketServiceImpl(
      PocketRepository pocketRepository,
      TransactionsUtils transactionsUtils,
      AccountRepository accountRepository
  ) {
    this.pocketRepository = pocketRepository;
    this.transactionsUtils = transactionsUtils;
    this.accountRepository = accountRepository;
  }

  @Override
  public SimpleMessageResponse depositFromAccount(Long ownerId, PocketTransferRequest transferRequest) {

    Account account = accountRepository.findById(transferRequest.accountNumber())
        .orElseThrow(() -> new NotFoundException("Account not found"));

    transactionsUtils.isAccountOwner(ownerId, account.getOwner().getId());

    Pocket pocket = pocketRepository.findById(transferRequest.pocketNumber())
        .orElseThrow(() -> new NotFoundException("Pocket does not belong to this account"));

    transactionsUtils.isThereEnoughBalanceToTransfer(account.getBalance(), transferRequest.amount());

    account.setBalance(account.getBalance().subtract(transferRequest.amount()));
    pocket.setBalance(pocket.getBalance().add(transferRequest.amount()));
    accountRepository.save(account);
    pocketRepository.save(pocket);

    return new SimpleMessageResponse(String.format("Deposit of %s to pocket '%s' completed", transferRequest.amount(), pocket.getName()));
  }

  @Override
  public SimpleMessageResponse create(Long ownerId, PocketCreationRequest creationRequest) {

    Account account = getAccountById(creationRequest.AccountNumber());

    transactionsUtils.isAccountOwner(ownerId, account.getOwner().getId());

    transactionsUtils.isAmountValidForCreatePocket(creationRequest.amount());

    transactionsUtils.isThereEnoughBalanceToTransfer(account.getBalance(),creationRequest.amount());

    transactionsUtils.isPocketNameValid(account,creationRequest.name());

    account.setBalance(account.getBalance().subtract(creationRequest.amount()));
    Pocket newPocket = new Pocket(
            null,
            creationRequest.name(),
            creationRequest.amount()
    );

    account.addPocket(newPocket);
    accountRepository.save(account);
    pocketRepository.save(newPocket);

    return new SimpleMessageResponse("The Pocket named " + creationRequest.name() + " was successfully created");
  }

  private Account getAccountById(Long id) {
    return accountRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Account not found"));
  }
}
