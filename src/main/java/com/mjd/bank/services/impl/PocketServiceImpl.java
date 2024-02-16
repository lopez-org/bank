package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.request.pocketDTO.PocketCreationRequest;
import com.mjd.bank.dtos.request.pocketDTO.PocketDepositRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.Pocket;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.PocketRepository;
import com.mjd.bank.services.AccountService;
import com.mjd.bank.services.PocketService;
import com.mjd.bank.utils.TransactionsUtils;
import org.springframework.stereotype.Service;

@Service
public class PocketServiceImpl implements PocketService {

  private final AccountService accountService;
  private final PocketRepository pocketRepository;
  private final TransactionsUtils transactionsUtils;

  public PocketServiceImpl(
      PocketRepository pocketRepository,
      TransactionsUtils transactionsUtils,
      AccountService accountService
  ) {
    this.pocketRepository = pocketRepository;
    this.transactionsUtils = transactionsUtils;
    this.accountService = accountService;
  }

  @Override
  public SimpleMessageResponse depositFromAccount(Long ownerId, PocketDepositRequest transferRequest) {

    Account account = accountService.getAccountById(transferRequest.accountNumber());

    transactionsUtils.isAccountOwner(ownerId, account.getOwner().getId());

    Pocket pocket = getPocketById(transferRequest.pocketNumber());

    transactionsUtils.isAmountValid(transferRequest.amount());
    transactionsUtils.isThereEnoughBalanceToTransfer(account.getBalance(), transferRequest.amount());

    account.setBalance(account.getBalance().subtract(transferRequest.amount()));
    pocket.setBalance(pocket.getBalance().add(transferRequest.amount()));
    accountService.save(account);
    save(pocket);

    return new SimpleMessageResponse(String.format("Deposit of %s to pocket '%s' completed", transferRequest.amount(), pocket.getName()));
  }

  @Override
  public SimpleMessageResponse create(Long ownerId, PocketCreationRequest creationRequest) {

    Account account = accountService.getAccountById(creationRequest.AccountNumber());

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
    accountService.save(account);
    save(newPocket);

    return new SimpleMessageResponse("The Pocket named " + creationRequest.name() + " was successfully created");
  }

  @Override
  public SimpleMessageResponse depositFromPocket(Long ownerId, PocketDepositRequest withdrawRequest) {

    Account account = accountService.getAccountById(withdrawRequest.accountNumber());

    transactionsUtils.isAccountOwner(ownerId, account.getOwner().getId());

    Pocket pocket = getPocketById(withdrawRequest.pocketNumber());

    transactionsUtils.isAmountValid(withdrawRequest.amount());
    transactionsUtils.isThereEnoughBalanceToTransfer(pocket.getBalance(), withdrawRequest.amount());

    pocket.setBalance(pocket.getBalance().subtract(withdrawRequest.amount()));
    account.setBalance(account.getBalance().add(withdrawRequest.amount()));
    save(pocket);
    accountService.save(account);

    return new SimpleMessageResponse(String.format("Deposit of %s to account '%s' completed", withdrawRequest.amount(), account.getNumber()));
  }

  @Override
  public Pocket getPocketById(Long id) {
    return pocketRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Pocket does not belong to this account"));
  }

  @Override
  public void save(Pocket pocket) {
    pocketRepository.save(pocket);
  }
}
