package com.mjd.bank.controllers;

import com.mjd.bank.dtos.response.TransactionDTO;
import com.mjd.bank.services.TransactionService;
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
  public List<TransactionDTO> getTransactions(@RequestParam(required = false) Long accountNumber, @RequestParam(required = false) String type) {
    return transactionService.getTransactions(accountNumber, type);
  }
}
