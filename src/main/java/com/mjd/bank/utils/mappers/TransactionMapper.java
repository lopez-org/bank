package com.mjd.bank.utils.mappers;

import com.mjd.bank.dtos.response.TransactionDTO;
import com.mjd.bank.entities.Transaction;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

  public TransactionDTO toTransactionDTO(Transaction transaction) {
    return new TransactionDTO()
        .setId(transaction.getId())
        .setAccountNumber(transaction.getFrom().getNumber())
        .setType(transaction.getType().toString())
        .setAmount(transaction.getAmount())
        .setDate(transaction.getCreatedAt().toString())
        .setDescription(transaction.getDescription());
  }

  public List<TransactionDTO> toTransactionDTOList(List<Transaction> transactionList) {
    return transactionList.stream().map(this::toTransactionDTO).collect(Collectors.toList());
  }
}
