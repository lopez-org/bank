package com.mjd.bank.services.impl;

import com.mjd.bank.dtos.response.TransactionDTO;
import com.mjd.bank.entities.Account;
import com.mjd.bank.entities.Transaction;
import com.mjd.bank.entities.TransactionStatus;
import com.mjd.bank.entities.TransactionType;
import com.mjd.bank.exceptions.NotFoundException;
import com.mjd.bank.repositories.AccountRepository;
import com.mjd.bank.repositories.TransactionRepository;
import com.mjd.bank.services.TransactionService;
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

  public TransactionServiceImpl(TransactionRepository transactionRepository,
      AccountRepository accountRepository,
      TransactionMapper transactionMapper
  ) {
    this.transactionRepository = transactionRepository;
    this.accountRepository = accountRepository;
    this.transactionMapper = transactionMapper;
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
    Account account = accountRepository.findById(accountNumber)
        .orElseThrow(() -> new NotFoundException("Account not found"));

    Example<Transaction> example = transactionMapper.buildExampleTransactionQuery(
        account, type, status, dateFrom, dateTo, amountFrom, amountTo
    );

    List<Transaction> result = transactionRepository.findAll(example);

    return transactionMapper.toTransactionDTOList(result);
  }
}
