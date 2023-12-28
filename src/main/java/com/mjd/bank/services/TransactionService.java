package com.mjd.bank.services;

import com.mjd.bank.dtos.response.TransactionDTO;
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

}
