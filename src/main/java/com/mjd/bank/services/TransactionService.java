package com.mjd.bank.services;

import com.mjd.bank.dtos.request.transactionDTO.TransactionTransferRequest;
import com.mjd.bank.dtos.response.SimpleMessageResponse;
import com.mjd.bank.dtos.response.TransactionDTO;
import com.mjd.bank.entities.Transaction;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface TransactionService {
  List<TransactionDTO> getTransactions(
      Long accountNumber,
      String type,
      String status,
      LocalDateTime dateFrom,
      LocalDateTime dateTo,
      BigDecimal amountFrom,
      BigDecimal amountTo
  );

  SimpleMessageResponse transfer(Long ownerId, TransactionTransferRequest body);
  void save(Transaction transaction);

}
