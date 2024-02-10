package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.request.transactionDTO.TransactionTransferRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.dtos.response.TransactionDTO;
import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.Transaction;
import com.mjd.bank.entities.TransactionStatus;
import com.mjd.bank.entities.TransactionType;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.repositories.TransactionRepository;
import com.mjd.bank.services.TransactionService;
import com.mjd.bank.utils.TransactionsUtils;
import com.mjd.bank.utils.mappers.TransactionMapper;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final AccountRepository accountRepository;
  private final TransactionRepository transactionRepository;
  private final TransactionMapper transactionMapper;
  private final TransactionsUtils transactionsUtils;

  public TransactionServiceImpl(TransactionRepository transactionRepository,
      AccountRepository accountRepository,
      TransactionMapper transactionMapper,
      TransactionsUtils transactionsUtils
  ) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
    this.transactionMapper = transactionMapper;
    this.transactionsUtils = transactionsUtils;
  }

  @Override
  public List<TransactionDTO> getTransactions(
      Long accountNumber,
      String type,
      String status,
      LocalDateTime dateFrom,
      LocalDateTime dateTo,
      BigDecimal amountFrom,
      BigDecimal amountTo
  ) {
    Account account = getAccountById(accountNumber);

    Example<Transaction> example = transactionMapper.buildExampleTransactionQuery(
        account, type, status, dateFrom, dateTo, amountFrom, amountTo
    );

    List<Transaction> result = transactionRepository.findAll(example);

    return transactionMapper.toTransactionDTOList(result);
  }

  @Override
  public SimpleMessageResponse transfer(Long ownerId, TransactionTransferRequest body) {

    Account accountFrom = getAccountById(body.from());
    transactionsUtils.isAccountOwner(ownerId, accountFrom.getOwner().getId());

    Account accountTo = getAccountById(body.to());
    transactionsUtils.validateTransferAmounts(accountFrom, body.amount());
    transactionsUtils.transferMoneyBetweenAccounts(accountFrom, accountTo, body.amount());

    saveAccount(accountFrom);
    saveAccount(accountTo);
    saveTransaction(accountFrom, accountTo, body.description(), body.amount());

    return new SimpleMessageResponse("Transfer completed successfully");
  }

  private Account getAccountById(Long id) {
    return accountRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Account not found"));
  }

  public void saveTransaction(Account accountFrom, Account accountTo, String description, BigDecimal amount) {
    transactionRepository.save(
        transactionsUtils.buildTransaction(
            accountFrom,
            accountTo,
            amount,
            TransactionType.TRANSFER,
            TransactionStatus.COMPLETED,
            description
        )
    );
  }

  private void saveAccount(Account account) {
    accountRepository.save(account);
  }
}
