package com.mjd.bank.services;

import com.mjd.bank.dtos.response.TransactionDTO;
import java.util.List;

public interface TransactionService {
  List<TransactionDTO> getTransactions(Long accountNumber, String type);

}
