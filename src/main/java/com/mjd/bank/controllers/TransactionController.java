package com.mjd.bank.controllers;

import com.mjd.bank.dtos.response.TransactionDTO;
import com.mjd.bank.services.TransactionService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @GetMapping
  public List<TransactionDTO> getTransactions(
      @RequestParam(required = false, name = "account_number") Long accountNumber,
      @RequestParam(required = false) String type,
      @RequestParam(required = false) String status,
      @RequestParam(required = false, name = "date_from") LocalDateTime dateFrom,
      @RequestParam(required = false, name = "date_to") LocalDateTime dateTo,
      @RequestParam(required = false, name = "amount_from") BigDecimal amountFrom,
      @RequestParam(required = false, name = "amount_to") BigDecimal amountTo
  ) {
    return transactionService.getTransactions(accountNumber, type, status, dateFrom, dateTo, amountFrom, amountTo);
  }
}